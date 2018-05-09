package cc.openkit.admin.util;

import cc.openkit.admin.controller.admin.AdminController;
import cc.openkit.admin.model.Admin;
import cc.openkit.admin.model.User;
import cc.openkit.admin.service.admin.AdminService;
import cc.openkit.admin.service.user.UserService;
import cc.openkit.common.KitUtil;
import org.apache.log4j.Logger;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

public class AppUtil {

    private Logger log = Logger.getLogger(AdminController.class);

    private static UserService userService = ApplicationContextHelper.getBean(UserService.class);

    /**
     * 密码加盐处理工具类
     * @param s 需要处理的原文字
     * @return 处理后的返回值
     */
    public static String md5pwd(String s){
        return KitUtil.md5(s+StaticFinalVar.YAN);
    }

    /**
     * APP校验工具(游客)
     * @param request
     * @return
     */
    public static Boolean isApp(HttpServletRequest request){
        String reqKey = request.getHeader("reqKey");
        String reqDate = request.getHeader("reqDate");
        // 上线需要删除的代码
        if("openkitadmin".equals(reqKey)){
            return true;
        }

        if(!KitUtil.feikong(reqKey)||!KitUtil.feikong(reqDate)){
            return false;
        }
        String key = KitUtil.md5(StaticFinalVar.APP_ID+StaticFinalVar.APP_YAN+StaticFinalVar.APP_KEY+StaticFinalVar.APP_YAN+reqDate);
        return key.equals(reqKey) ? true : false;
    }

    /**
     * APP校验工具(登录用户)
     * @param request
     * @return
     */

    public static Boolean isAppAndLogin(HttpServletRequest request){
        String reqKey = request.getHeader("reqToken");
        String reqDate = request.getHeader("reqDate");
        String uid = request.getHeader("uid");

        // 添加用户的查询 方法，获取token
        User user = userService.queryByUUID(uid);
        String token = user.getUserToken();

        // 上线需要删除的代码
        if("openkitadmin".equals(reqKey)){
            return true;
        }

        if(!KitUtil.feikong(reqKey)||!KitUtil.feikong(reqDate)){
            return false;
        }
        String key = KitUtil.md5(uid + StaticFinalVar.APP_YAN + token + StaticFinalVar.APP_YAN + reqDate);
        return key.equals(reqKey) ? true : false;
    }

    public static void main(String[] args) {
        System.out.println(md5pwd("admin"));
    }

}
