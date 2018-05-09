package cc.openkit.admin.controller.api;

import cc.openkit.admin.controller.user.UserController;
import cc.openkit.admin.model.Sign;
import cc.openkit.admin.model.User;
import cc.openkit.admin.service.g.GGroupLimitService;
import cc.openkit.admin.service.sign.SignService;
import cc.openkit.admin.service.user.UserService;
import cc.openkit.admin.util.AppUtil;
import cc.openkit.admin.util.StaticFinalVar;
import cc.openkit.common.KitUtil;
import cc.openkit.kitIsNull.KitVerification;
import com.alibaba.fastjson.JSONObject;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 统一的前端用户登录账号
 *
 * @author bigben
 * @date 2018.2.5
 */
@Controller
@Scope("prototype")
@RequestMapping("/apiUser")
public class ApiUserController {
    private Logger log = Logger.getLogger(UserController.class);

    @Resource
    private UserService userService;
    @Resource
    private GGroupLimitService gGroupLimitService;
    @Resource
    private SignService signService;

    /**
     * 用户注册信息提交，主要是手机号码验证码注册也就是说手机号码，验证码，kst 几个参数必传，其他的可以根据APP的具体需求进行
     * （已经跟新）
     * @param request
     * @return
     */

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public Object register(HttpServletRequest request){
        log.info("APP端注册接口调用 开始");
        Map<String, Object> map = new HashMap<String, Object>();
        // APP验证
        if(!AppUtil.isApp(request)){
            return JSONObject.toJSON(KitUtil.returnMap("4001", StaticFinalVar.APP_ERR));
        }

        // APP验证通过
        // 1. 验证我们的手机号码和验证码
        // 1.1. 首先获取手机号码和验证码，验证信息
        String userPhone = request.getParameter("userPhone");// 手机号码
        String s = request.getParameter("sign");// 验证码
        String kst = request.getParameter("KST");// 验证码唯一标识
        // 1.2. 先验证手机号码和验证码是否可用
        Map<String, Object> signMap = new HashMap<String, Object>();
        signMap = signService.isSignOk(userPhone,kst,s);
        if(!"200".equals(signMap.get("code"))){
            return JSONObject.toJSON(signMap);
        }
        // 2. 验证码验证成功开始写这注册，我们需要获取所有的参数

        String userName = request.getParameter("userName");
        String userLoginName = request.getParameter("userLoginName");
        String userPassword = request.getParameter("userPassword");
        String userEmail = request.getParameter("userEmail");
        String userImg = request.getParameter("userImg");
        String userRecommendPerson = request.getParameter("userRecommendPerson");

        // 3. 验证是不是已经存在
        User user = new User();
        user.setUserPhone(userPhone);
        // 4. 测试是不是已经注册
        int i = userService.queryCount(user);

        if(i>0){
            return JSONObject.toJSON(KitUtil.returnMap("101",StaticFinalVar.HAVE_PHONE));
        }
        // 3.1 验证推荐码也就是推荐人是否存在
        User us = new User();
        if(userRecommendPerson!=null && !"".equals(userRecommendPerson)){
            us.setUserRecommendCode(userRecommendPerson);
            User u = userService.queryOne(us);
            if(u==null){
                return JSONObject.toJSON(KitUtil.returnMap("101",StaticFinalVar.HAVE_NOT_RECOMMEND));
            }
        }

        // 随机生成推荐码
        String r = "";

        do {
            r = KitUtil.getStringRandom(StaticFinalVar.RECOMMEND_LENGTH);
            us.setUserRecommendCode(r);
            int count = userService.queryCount(us);
            if(count==0){
                break;
            }
        } while (true);

        // 4. 如果不存在，封装数据
        user.setUserId(KitUtil.uuid());
        user.setUserName(userName);
        user.setUserLoginName(userLoginName);
        user.setUserPassword(userPassword);
        user.setUserEmail(userEmail);
        user.setUserImg(userImg);
        user.setUserToken(KitUtil.uuid());
        user.setUserTime(new Date());
        user.setUserType(1);
        user.setUserRecommendPerson(r);

        // 5. 保存数据
        int ii = userService.save(user);
        if(ii == 1){
            // 修改验证码为已经使用

            Sign s2 = new Sign();
            s2.setSignId(kst);
            s2.setSignType(2);
            signService.updateSelective(s2);

            map = KitUtil.returnMap("200",StaticFinalVar.REGISTER_OK);
            map.put("user",user);
        }else{
            KitUtil.returnMap("101",StaticFinalVar.REGISTER_ERR);
        }

        return  JSONObject.toJSON(map);
    }

    /**
     * 用户登录，账号密码，邮箱密码，手机号码密码
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public Object login(HttpServletRequest request){
        log.info("APP端用户登录调用 开始");
        Map<String, Object> map = new HashMap<String, Object>();
        // APP验证
        if(!AppUtil.isApp(request)){
            return JSONObject.toJSON(KitUtil.returnMap("4001", StaticFinalVar.APP_ERR));
        }
        Map<String, Object> signMap = new HashMap<String, Object>();

        // 获取到登录的账号和密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();

        // 1. 如果是手机号码
        if(KitVerification.isMobile(username)){
            user.setUserPhone(username);
            User returnUser = userService.queryOne(user);
            if(returnUser==null){
                return JSONObject.toJSON(KitUtil.returnMap("101",StaticFinalVar.HAVE_NOT_PHONE));
            }
            if(!returnUser.getUserPassword().equals(password)){
                return JSONObject.toJSON(KitUtil.returnMap("101",StaticFinalVar.USERNAME_OR_PWD_ERR));
            }
            map = KitUtil.returnMap("200","");
            map.put("user",returnUser);
            return JSONObject.toJSON(map);
        }

        // 2. 如果是邮箱
        if(KitVerification.isEmail(username)){
            user.setUserEmail(username);
            User returnUser = userService.queryOne(user);
            if(returnUser==null){
                return JSONObject.toJSON(KitUtil.returnMap("101",StaticFinalVar.HAVE_NOT_EMAIL));
            }
            if(!returnUser.getUserPassword().equals(password)){
                return JSONObject.toJSON(KitUtil.returnMap("101",StaticFinalVar.USERNAME_OR_PWD_ERR));
            }
            map = KitUtil.returnMap("200","");
            map.put("user",returnUser);
            return JSONObject.toJSON(map);
        }

        // 3. 否则
        user.setUserLoginName(username);
        User returnUser = userService.queryOne(user);

        // 3.1 看这个用户是不是存在推荐人，如果存在，查出推荐人并返回
        slectRecommend(returnUser);

        if(returnUser==null){
            return JSONObject.toJSON(KitUtil.returnMap("101",StaticFinalVar.HAVE_NOT_USER));
        }
        if(!returnUser.getUserPassword().equals(password)){
            return JSONObject.toJSON(KitUtil.returnMap("101",StaticFinalVar.USERNAME_OR_PWD_ERR));
        }
        map = KitUtil.returnMap("200","");
        map.put("user",returnUser);
        return JSONObject.toJSON(map);
    }

    /**
     * 手机号码找回密码，根据手机号码修改密码，也就是找回密码
     * @param request
     * @return
     */
    @RequestMapping(value = "/seekPwd", method = RequestMethod.POST)
    @ResponseBody
    public Object seekPwd(HttpServletRequest request){
        log.info("APP端用户手机号码修改密码方法调用 开始");
        Map<String, Object> map = new HashMap<String, Object>();
        // APP验证
        if(!AppUtil.isApp(request)){
            return JSONObject.toJSON(KitUtil.returnMap("4001", StaticFinalVar.APP_ERR));
        }

        // 先验证手机号码是否存在
        // 获取到登录的账号和密码

        String userPhone = request.getParameter("userPhone");// 手机号码
        String s = request.getParameter("sign");// 验证码
        String kst = request.getParameter("KST");// 验证码唯一标识

        // 验证手机号码有没有注册
        User user = new User();
        user.setUserPhone(userPhone);
        User returnuser = userService.queryOne(user);
        if(returnuser==null){
            // 如果找不到，提醒手机号码未注册
            return JSONObject.toJSON(KitUtil.returnMap("101",StaticFinalVar.HAVE_NOT_PHONE));
        }

        // 否则先验证手机号码和验证码是否可用
        Map<String, Object> signMap = new HashMap<String, Object>();
        signService.isSignOk(userPhone,kst,s);
        if(!"200".equals(signMap.get("code"))){
            return JSONObject.toJSON(signMap);
        }

        // 验证码通过之后，跟新用户密码，修改用户的Token保存并返回信息
        String password = request.getParameter("password");
        returnuser.setUserPassword(password);
        returnuser.setUserToken(KitUtil.uuid());
        int i = userService.updateSelective(user);
        if(i==1){
            map = KitUtil.returnMap("200","");
            map.put("user",returnuser);
            return JSONObject.toJSON(map);
        }else{
            slectRecommend(returnuser);
            map = KitUtil.returnMap("101",StaticFinalVar.SEEK_PWD_ERR);
            map.put("user",returnuser);
            return JSONObject.toJSON(map);
        }
    }


    /**
     * 手机号码验证码登录
     * @param request
     * @return
     */
    @RequestMapping(value = "/phoneAndSign", method = RequestMethod.POST)
    @ResponseBody
    public Object phoneAndSign(HttpServletRequest request){
        log.info("APP端用户账号和验证码调用 开始");
        Map<String, Object> map = new HashMap<String, Object>();
        // APP验证
        if(!AppUtil.isApp(request)){
            return JSONObject.toJSON(KitUtil.returnMap("4001", StaticFinalVar.APP_ERR));
        }

        // 先验证手机号码是否存在
        // 获取到登录的账号和密码

        String userPhone = request.getParameter("userPhone");// 手机号码
        String s = request.getParameter("sign");// 验证码
        String kst = request.getParameter("KST");// 验证码唯一标识

        // 验证手机号码有没有注册
        User user = new User();
        user.setUserPhone(userPhone);
        User returnuser = userService.queryOne(user);
        if(returnuser==null){
            // 如果找不到，提醒手机号码未注册
            return JSONObject.toJSON(KitUtil.returnMap("101",StaticFinalVar.HAVE_NOT_PHONE));
        }

        // 否则先验证手机号码和验证码是否可用
        Map<String, Object> signMap = new HashMap<String, Object>();
        signService.isSignOk(userPhone,kst,s);
        if(!"200".equals(signMap.get("code"))){
            return JSONObject.toJSON(signMap);
        }

        // 验证码通过之后，修改用户的Token保存并返回信息
        returnuser.setUserToken(KitUtil.uuid());
        int i = userService.updateSelective(user);
        if(i==1){
            map = KitUtil.returnMap("200","");
            map.put("user",returnuser);
            return JSONObject.toJSON(map);
        }else{
            slectRecommend(returnuser);
            map = KitUtil.returnMap("101",StaticFinalVar.LOGIN_ERR);
            map.put("user",returnuser);
            return JSONObject.toJSON(map);
        }
    }

    /**
     * 根据老密码修改密码登录到APP修改
     * @param request
     * @return
     */
    @RequestMapping(value = "/seekPwdByOldPwd", method = RequestMethod.POST)
    @ResponseBody
    public Object seekPwdByOldPwd(HttpServletRequest request){
        log.info("APP端用户根据老密码修改新密码接口调用 开始");
        Map<String, Object> map = new HashMap<String, Object>();
        // APP验证
        if(!AppUtil.isAppAndLogin(request)){
            return JSONObject.toJSON(KitUtil.returnMap("4001", StaticFinalVar.APP_ERR));
        }
        // APP验证OK先验证手机号码
        String uid = request.getParameter("uid");
        String oldPwd = request.getParameter("oldPwd");
        String newPwd = request.getParameter("newPwd");
        User user = new User();
        user.setUserId(uid);
        user.setUserPassword(oldPwd);
        int i = userService.queryCount(user);
        if(i==0){
            return JSONObject.toJSON(KitUtil.returnMap("101",StaticFinalVar.USERNAME_OR_PWD_ERR));
        }
        user.setUserPassword(newPwd);
        int ii = userService.updateSelective(user);

        return JSONObject.toJSON( ii==1 ? KitUtil.returnMap("101",StaticFinalVar.UPDATE_OK) : KitUtil.returnMap("101",StaticFinalVar.UPDATE_ERR));
    }

    /**
     * 公共的查看是否存在推荐人的方法，如果存在则直接给他
     * @param returnUser
     */
    private void slectRecommend(User returnUser) {
        if(KitUtil.feikong(returnUser.getUserRecommendPerson())){
            User us = new User();
            us.setUserRecommendCode(returnUser.getUserRecommendPerson());
            User u = userService.queryOne(us);
            returnUser.setUserRecommendName(u.getUserName());
        }
    }


}
