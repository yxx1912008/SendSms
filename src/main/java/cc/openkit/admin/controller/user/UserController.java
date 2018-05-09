package cc.openkit.admin.controller.user;

import cc.openkit.admin.model.AdvHello;
import cc.openkit.admin.model.GGroupLimit;
import cc.openkit.admin.model.User;
import cc.openkit.admin.service.g.GGroupLimitService;
import cc.openkit.admin.service.g.GGroupService;
import cc.openkit.admin.service.user.UserService;
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
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 *
 * @author bigben
 * @date 2018.2.5
 */
@Controller
@Scope("prototype")
@RequestMapping("/user")
public class UserController {
    private Logger log = Logger.getLogger(UserController.class);

    @Resource
    private UserService userService;
    @Resource
    private GGroupLimitService gGroupLimitService;

    /**
     * 跳转到列表
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public ModelAndView getAll(HttpServletRequest request){

        // 权限验证。这里的最后的 5 就是上面我们找到菜单的ID，需要在这里查找
        GGroupLimit gGroupLimit = gGroupLimitService.testGroup(request, 24);

        ModelAndView mv = new ModelAndView();

        // 没有权限，返回错误页面
        if(gGroupLimit==null){
            mv.setViewName("/admin/err/no_group_err");
            mv.addObject("msg", StaticFinalVar.NO_GROUP_MSG+ StaticFinalVar.CALL_GROUP);
            return mv;
        }

        // 取值
        mv.setViewName("/admin/user/list"); // 需要跳转的页面
        mv.addObject("kitG",gGroupLimit);
        return mv;
    }

    /**
     * 获取列表数据
     * @return
     */
    @RequestMapping(value = "/getAllJson", method = RequestMethod.POST)
    @ResponseBody
    public Object getAllJson(HttpServletRequest request)throws Exception{
        log.info("获取用户列表数据");

        Map<String,Object> map = new HashMap<String,Object>();
        // 分页需要的参数
        String page = request.getParameter("page");// 获得页数
        String limit = request.getParameter("limit");// 获得每页显示条数
        String search = request.getParameter("search");// 获取搜索条件

        // 封装数据
        User user = new User();
        if(KitUtil.feikong(search)){
            user.setUserName(search);
        }

        // 分页查询
        List<User> advHelloList = userService.queryPageListByWhere(user, Integer.valueOf(page), Integer.valueOf(limit));
        // 获取查询到的总数
        int size = userService.queryCount(user);

        // 返回数据
        map.put("code",0);
        map.put("msg","");
        map.put("count",size);
        map.put("data",advHelloList);

        return JSONObject.toJSON(map);
    }

    /**
     * 修改开关状态
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateType", method = RequestMethod.POST)
    @ResponseBody
    public Object updateType(HttpServletRequest request) throws Exception{
        log.info("用户列表数据 》 修改 》 修改开关状态");
        //取值
        String id = request.getParameter("id");
        String kaiguan = request.getParameter("kaiguan");

        // 封装
        User user = new User();
        user.setUserId(id);
        user.setUserType(Integer.valueOf(kaiguan));
        return userService.updateSelective(user) == 1 ? KitUtil.returnMap("200",StaticFinalVar.UPDATE_OK) : KitUtil.returnMap("101",StaticFinalVar.UPDATE_ERR);
    }

    /**
     * 删除
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Object del(HttpServletRequest request) throws Exception{
        log.info("用户列表数据 》 删除");

        String id = request.getParameter("id");
        int i = userService.deleteByUUId(id);
        return i==1?KitUtil.returnMap("200",StaticFinalVar.DEL_OK):KitUtil.returnMap("101",StaticFinalVar.DEL_ERR);
    }

    /**
     * 去添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/goAdd", method = RequestMethod.GET)
    public Object goAdd(HttpServletRequest request){
        log.info("用户列表数据 》 添加 》 跳转");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/user/add");
        return mv;
    }

    /**
     * 添加
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public Object add(HttpServletRequest request) throws Exception{
        log.info("用户管理 》 添加 》 保存");

        // 取值
        String userName = request.getParameter("userName");
        String imgurl = request.getParameter("imgurl");
        String userLoginName = request.getParameter("userLoginName");
        String userPassword = request.getParameter("userPassword");
        String userPasswordAgen = request.getParameter("userPasswordAgen");
        String userPhone = request.getParameter("userPhone");
        String userEmail = request.getParameter("userEmail");

        // 封装对象
        User user = new User();
        user.setUserId(KitUtil.uuid());
        user.setUserName(userName);
        user.setUserImg(imgurl);
        user.setUserLoginName(userLoginName);
        user.setUserPassword(AppUtil.md5pwd(userPassword));
        user.setUserPhone(userPhone);
        user.setUserEmail(userEmail);
        user.setUserTime(new Date());
        user.setUserType(1);

        // 保存并返回
        return JSONObject.toJSON(userService.save(user)==1 ? KitUtil.returnMap("200",StaticFinalVar.ADD_OK) : KitUtil.returnMap("101",StaticFinalVar.ADD_ERR));

    }


    /**
     * 修改前的查询
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/goUpdate", method = RequestMethod.GET)
    public ModelAndView goUpdate(HttpServletRequest request) throws Exception{
        log.info("用户管理 》 修改 》 跳转");

        String id = request.getParameter("id");
        User user = userService.queryByUUID(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/user/update");
        mv.addObject("kitModel",user);
        return mv;
    }

    /**
     * 修改
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public Object update(HttpServletRequest request) throws Exception{

        log.info("用户管理 》 修改 》 提交");

        // 取值
        // 取值

        String userId = request.getParameter("userId");
        String userName = request.getParameter("userName");
        String imgurl = request.getParameter("imgurl");
        String userLoginName = request.getParameter("userLoginName");
        String userPassword = request.getParameter("userPassword");
        String userPasswordAgen = request.getParameter("userPasswordAgen");
        String userPhone = request.getParameter("userPhone");
        String userEmail = request.getParameter("userEmail");

        // 封装对象
        User user = new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setUserImg(imgurl);
        user.setUserLoginName(userLoginName);
        if(KitUtil.feikong(userPassword)){
            user.setUserPassword(AppUtil.md5pwd(userPassword));
        }
        user.setUserPhone(userPhone);
        user.setUserEmail(userEmail);
        user.setUserUpdateTime(new Date());


        // 修改保存并返回参数
        return JSONObject.toJSON(userService.updateSelective(user)==1 ? KitUtil.returnMap("200",StaticFinalVar.UPDATE_OK) : KitUtil.returnMap("101",StaticFinalVar.UPDATE_ERR));
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
