package cc.openkit.admin.controller.g;

import cc.openkit.admin.model.Admin;
import cc.openkit.admin.model.GGroupLimit;
import cc.openkit.admin.model.WebSetting;
import cc.openkit.admin.service.admin.AdminService;
import cc.openkit.admin.service.g.GGroupLimitService;
import cc.openkit.admin.vo.LimitVo;
import cc.openkit.common.KitUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.json.JsonObject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户组所有的权限管理
 *
 * @author bigben
 * @date 2018.2.5
 */
@Controller
@Scope("prototype")
@RequestMapping("/gGroupLimit")
public class GGroupLimitController {
    private Logger log = Logger.getLogger(GGroupLimitController.class);

    @Resource
    private GGroupLimitService gGroupLimitService;
    @Resource
    private AdminService adminService;

    /**
     * 每隔页面都要请求，然后看这个页面是否具有这个权限或者说这个功能是否具有增删改查
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/getGroup", method = RequestMethod.POST)
    @ResponseBody
    public Object getGroup(HttpServletRequest request){

        Map<String, Object> map = new HashMap<String, Object>();

        log.info("获取授权");

        HttpSession session = request.getSession();
        // 获取 管理用户id
        String adminid = request.getParameter("adminid");
        // 获取session中的kitWeb
        String kitWeb = request.getParameter("kitWeb");
        // 获取Action的ID
        String actionid = request.getParameter("actionid");
        // 判断如果没有kitweb说明已经过期了
        if(kitWeb==null||"".equals(kitWeb)){
            map = KitUtil.returnMap("101","由于你长时间没有操作，请重新登录!");
            return JSONObject.toJSON(map);
        }
        if(adminid==null||"".equals(adminid)){
            map = KitUtil.returnMap("101","由于你长时间没有操作，请重新登录!");
            return JSONObject.toJSON(map);
        }
        int id = 0;
        if(actionid!=null&&!"".equals(actionid)){
            id = Integer.parseInt(actionid);
        }

        // 查看分组
        Admin admin = adminService.queryByUUID(adminid);
        // 根据分组查看是否有这个权限

        List<LimitVo> limitVoList = gGroupLimitService.getModelByGroupId(1);

        GGroupLimit gGroupLimit = gGroupLimitService.getModelByGroupIdAndLimitId(admin.getGroupId(), id);
        if(gGroupLimit == null){
            map = KitUtil.returnMap("102","没有此权限，请与管理员联系!");
            return JSONObject.toJSON(map);
        }
        map.put("limit",gGroupLimit);
        map.put("code","200");
        return JSONObject.toJSON(map);
    }

}
