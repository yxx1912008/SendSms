package cc.openkit.admin.controller.file;

import cc.openkit.admin.controller.common.ApiCommon;
import cc.openkit.admin.model.FileQiniu;
import cc.openkit.admin.model.GGroupLimit;
import cc.openkit.admin.model.PushJpush;
import cc.openkit.admin.service.file.qiniu.FileQiniuService;
import cc.openkit.admin.service.g.GGroupLimitService;
import cc.openkit.admin.util.StaticFinalVar;
import cc.openkit.common.KitUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;


/**
 * 七牛云
 */

@Controller
@Scope("prototype")
@RequestMapping("/file")
public class FileController {

    private Logger log = Logger.getLogger(FileController.class);

    @Autowired
    private FileQiniuService fileQiniuService;
    @Autowired
    private GGroupLimitService gGroupLimitService;


    /**
     * 去修改
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ModelAndView getAll(HttpServletRequest request){

        // 权限验证。这里的最后的 5 就是上面我们找到菜单的ID，需要在这里查找
        GGroupLimit gGroupLimit = gGroupLimitService.testGroup(request, 19);

        ModelAndView mv = new ModelAndView();

        // 没有权限，返回错误页面
        if(gGroupLimit==null){
            mv.setViewName("/admin/err/no_group_err");
            mv.addObject("msg", StaticFinalVar.NO_GROUP_MSG+StaticFinalVar.CALL_GROUP);
            return mv;
        }
        // 取值
        FileQiniu fileQiniu = fileQiniuService.queryById(1);

        // 取值
        mv.setViewName("/admin/file/update"); // 需要跳转的页面
        mv.addObject("kitG",gGroupLimit);
        mv.addObject("qiniu",fileQiniu);
        return mv;
    }



    /**
     * 修改七牛配置文件
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/updateQiniu", method = RequestMethod.POST)
    @ResponseBody
    public Object add(HttpServletRequest request) throws Exception{
        log.info("极光推送参数 》 修改 》 保存");

        // 取值
        String fqAccesskey = request.getParameter("fqAccesskey");
        String fqSecretkey = request.getParameter("fqSecretkey");
        String fqBucket = request.getParameter("fqBucket");
        String fqUrl = request.getParameter("fqUrl");
        String fqZone = request.getParameter("fqZone");

        // 封装对象
        FileQiniu fileQiniu = new FileQiniu();
        fileQiniu.setFqId(1);
        fileQiniu.setFqAccesskey(fqAccesskey);
        fileQiniu.setFqSecretkey(fqSecretkey);
        fileQiniu.setFqBucket(fqBucket);
        fileQiniu.setFqUrl(fqUrl);
        fileQiniu.setFqZone(fqZone);

        // 保存并返回
        return JSONObject.toJSON(fileQiniuService.updateSelective(fileQiniu)==1 ? KitUtil.returnMap("200", StaticFinalVar.ADD_OK) : KitUtil.returnMap("101",StaticFinalVar.ADD_ERR));
    }
}
