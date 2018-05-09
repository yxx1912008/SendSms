package cc.openkit.admin.controller.api;

import cc.openkit.admin.model.Area;
import cc.openkit.admin.service.area.AreaService;
import cc.openkit.admin.util.AppUtil;
import cc.openkit.admin.util.StaticFinalVar;
import cc.openkit.common.KitUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 公共的一些方法
 */
@Controller
@Scope("prototype")
@RequestMapping("/api")
public class ApiController {

    private Logger log = Logger.getLogger(ApiController.class);

    @Resource
    private AreaService areaService;

    /**
     * 获取所有的区域
     * 验证：APP游客验证
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.POST)
    @ResponseBody
    public Object get(HttpServletRequest request){
        if(!AppUtil.isApp(request)){
            return JSONObject.toJSON(KitUtil.returnMap("4001", StaticFinalVar.APP_ERR));
        }
        List<Area> areas = areaService.queryAll();
        Map<String, Object> map = KitUtil.returnMap("200", "");
        map.put("kitList",areas);
        return JSONObject.toJSON(map);
    }

    /**
     * 根基你要的给你数据，可以单独获取是省，或者市，或者区
     *
     * 验证：APP游客验证
     * @param request
     * @param s country：国家，province：省，city：市，district：区
     * @return
     */
    @RequestMapping(value = "/getAllByLevel", method = RequestMethod.POST)
    @ResponseBody
    public Object getAllProvince(HttpServletRequest request, String s){
        if(!AppUtil.isApp(request)){
            return JSONObject.toJSON(KitUtil.returnMap("4001", StaticFinalVar.APP_ERR));
        }
        Area a = new Area();
        // 国家
        if("country".equals(s)){
            a.setLevel(0);
        }
        // 省
        if("province".equals(s)){
            a.setLevel(1);
        }
        // 市
        if("city".equals(s)){
            a.setLevel(2);
        }
        // 区
        if("district".equals(s)){
            a.setLevel(3);
        }
        List<Area> areas = areaService.queryListByWhere(a);
        Map<String, Object> map = KitUtil.returnMap("200", "");
        map.put("kitList",areas);
        return JSONObject.toJSON(map);
    }


    /**
     * 根据父级ID查看子类
     *
     * 验证：APP游客验证
     * @param request
     * @return
     */
    @RequestMapping(value = "/getAllByPid", method = RequestMethod.POST)
    @ResponseBody
    public Object getAllByPid(HttpServletRequest request,int pid){
        if(!AppUtil.isApp(request)){
            return JSONObject.toJSON(KitUtil.returnMap("4001", StaticFinalVar.APP_ERR));
        }
        Area a = new Area();
        a.setPid(pid);
        List<Area> areas = areaService.queryListByWhere(a);
        Map<String, Object> map = KitUtil.returnMap("200", "");
        map.put("kitList",areas);
        return JSONObject.toJSON(map);
    }
}
