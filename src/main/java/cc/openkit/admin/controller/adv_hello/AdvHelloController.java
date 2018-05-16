package cc.openkit.admin.controller.adv_hello;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import cc.openkit.admin.model.AdvHello;
import cc.openkit.admin.model.GGroupLimit;
import cc.openkit.admin.service.adv_hello.AdvHelloService;
import cc.openkit.admin.service.g.GGroupLimitService;
import cc.openkit.admin.util.StaticFinalVar;
import cc.openkit.common.KitUtil;

import com.alibaba.fastjson.JSONObject;

/**
 * APP端的首次登录欢迎页面
 *
 * @author bigben
 * @date 2018.2.5
 */
@Controller
@Scope("prototype")
@RequestMapping("/advhello")
public class AdvHelloController {
    private Logger             log = Logger.getLogger(AdvHelloController.class);

    @Resource
    private AdvHelloService    advHelloService;
    @Resource
    private GGroupLimitService gGroupLimitService;

    /**
     * 去首次打开APP广告列表页面
     * @returnA
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView index(HttpServletRequest request) {
        log.info("去首次打开APP广告列表页面");

        // 权限验证
        GGroupLimit gGroupLimit = gGroupLimitService.testGroup(request, 21);
        ModelAndView mv = new ModelAndView();
        // 没有权限，返回错误页面
        if (gGroupLimit == null) {
            mv.setViewName("/admin/err/no_group_err");
            mv.addObject("msg", StaticFinalVar.NO_GROUP_MSG + StaticFinalVar.CALL_GROUP);
            return mv;
        }
        // 取值
        mv.addObject("kitG", gGroupLimit);
        mv.setViewName("/admin/adv_hello/list");
        return mv;
    }

    /**
     * 获取列表数据
     * @return
     */
    @RequestMapping(value = "/getAllJson", method = RequestMethod.POST)
    @ResponseBody
    public Object getAllJson(HttpServletRequest request) {
        log.info("获取APP广告列表的数据");

        Map<String, Object> map = new HashMap<String, Object>();

        String page = request.getParameter("page");// 获得页数
        String limit = request.getParameter("limit");// 获得每页显示条数
        String search = request.getParameter("search");// 获取搜索条件

        //        封装数据
        AdvHello advHello = new AdvHello();
        if (KitUtil.feikong(search)) {
            advHello.setAhTitle(search);
        }

        // 分页查询
        List<AdvHello> advHelloList = advHelloService.queryPageListByWhere(advHello,
            Integer.valueOf(page), Integer.valueOf(limit));
        int size = advHelloService.queryCount(advHello);

        // 返回数据
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", size);
        map.put("data", advHelloList);

        return JSONObject.toJSON(map);

    }

    /**
     * 去添加页面
     * @param request
     * @return
     */
    @RequestMapping(value = "/goAdd", method = RequestMethod.GET)
    public Object goAdd(HttpServletRequest request) {
        log.info("APP首次登录幻灯片 》 添加 》 跳转");
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/adv_hello/add");
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
    public Object add(HttpServletRequest request) throws Exception {
        log.info("APP首次登录幻灯片 》 添加 》 保存");

        // 取值
        String ahTitle = request.getParameter("ahTitle");
        String ahSummary = request.getParameter("ahSummary");
        String ahSequence = request.getParameter("ahSequence");
        String imgurl = request.getParameter("imgurl");
        String ahUrl = request.getParameter("ahUrl");
        String ahStartAndEndTime = request.getParameter("ahStartAndEndTime");
        String ahType = request.getParameter("ahType");

        // 封装对象
        AdvHello advHello = new AdvHello();
        advHello.setAhId(KitUtil.uuid());
        advHello.setAhTitle(ahTitle);
        if (KitUtil.feikong(ahSummary)) {
            advHello.setAhSummary(ahSummary);
        }
        if (KitUtil.feikong(ahSequence)) {
            advHello.setAhSequence(Integer.valueOf(ahSequence));
        }
        advHello.setAhImg(imgurl);
        if (KitUtil.feikong(ahUrl)) {
            advHello.setAhUrl(ahUrl);
        }
        advHello.setAhType("on".equals(ahType) ? 1 : 2);
        // 分割字符串
        String[] time = ahStartAndEndTime.split(" - ");

        advHello.setAhStartTime(KitUtil.stringToData(time[0], "yyyy-MM-dd HH:mm:ss"));
        advHello.setAhEndTime(KitUtil.stringToData(time[1], "yyyy-MM-dd HH:mm:ss"));
        advHello.setAhTime(new Date());

        return JSONObject.toJSON(advHelloService.save(advHello) == 1 ? KitUtil.returnMap("200",
            StaticFinalVar.ADD_OK) : KitUtil.returnMap("101", StaticFinalVar.ADD_ERR));

    }

    /**
     * 删除
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/del", method = RequestMethod.POST)
    @ResponseBody
    public Object del(HttpServletRequest request) throws Exception {
        log.info("APP首次登录幻灯片 》 添加 》 删除");

        String id = request.getParameter("id");
        int i = advHelloService.deleteByUUId(id);
        return i == 1 ? KitUtil.returnMap("200", StaticFinalVar.DEL_OK) : KitUtil.returnMap("101",
            StaticFinalVar.DEL_ERR);
    }

    /**
     * 修改开关状态
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/updateType", method = RequestMethod.POST)
    @ResponseBody
    public Object updateType(HttpServletRequest request) throws Exception {
        log.info("APP首次登录幻灯片 》 添加 》 删除");
        //取值
        String id = request.getParameter("id");
        String kaiguan = request.getParameter("kaiguan");

        // 封装
        AdvHello advHello = new AdvHello();
        advHello.setAhId(id);
        advHello.setAhType(Integer.valueOf(kaiguan));
        return advHelloService.updateSelective(advHello) == 1 ? KitUtil.returnMap("200",
            StaticFinalVar.UPDATE_OK) : KitUtil.returnMap("101", StaticFinalVar.UPDATE_ERR);
    }

    /**
     * 修改前的查询
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/goUpdate", method = RequestMethod.GET)
    public ModelAndView goUpdate(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        AdvHello advHello = advHelloService.queryByUUID(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/adv_hello/update");
        mv.addObject("kitModel", advHello);
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
    public Object update(HttpServletRequest request) throws Exception {
        // 取值
        String ahId = request.getParameter("ahId");
        String ahTitle = request.getParameter("ahTitle");
        String ahSummary = request.getParameter("ahSummary");
        String ahSequence = request.getParameter("ahSequence");
        String imgurl = request.getParameter("imgurl");
        String ahUrl = request.getParameter("ahUrl");
        String ahStartAndEndTime = request.getParameter("ahStartAndEndTime");
        String ahType = request.getParameter("ahType");

        // 封装对象
        AdvHello advHello = new AdvHello();
        advHello.setAhId(ahId);
        advHello.setAhTitle(ahTitle);
        if (KitUtil.feikong(ahSummary)) {
            advHello.setAhSummary(ahSummary);
        }
        if (KitUtil.feikong(ahSequence)) {
            advHello.setAhSequence(Integer.valueOf(ahSequence));
        }
        advHello.setAhImg(imgurl);
        if (KitUtil.feikong(ahUrl)) {
            advHello.setAhUrl(ahUrl);
        }
        advHello.setAhType("on".equals(ahType) ? 1 : 2);
        // 分割字符串
        String[] time = ahStartAndEndTime.split(" - ");

        advHello.setAhStartTime(KitUtil.stringToData(time[0], "yyyy-MM-dd HH:mm:ss"));
        advHello.setAhEndTime(KitUtil.stringToData(time[1], "yyyy-MM-dd HH:mm:ss"));
        advHello.setAhTime(new Date());

        return JSONObject.toJSON(advHelloService.updateSelective(advHello) == 1 ? KitUtil
            .returnMap("200", StaticFinalVar.UPDATE_OK) : KitUtil.returnMap("101",
            StaticFinalVar.UPDATE_ERR));
    }

}
