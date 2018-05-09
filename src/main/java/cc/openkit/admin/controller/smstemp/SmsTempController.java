package cc.openkit.admin.controller.smstemp;

import cc.openkit.admin.controller.adv_hello.AdvHelloController;
import cc.openkit.admin.model.AdvHello;
import cc.openkit.admin.model.GGroupLimit;
import cc.openkit.admin.model.sms.YtxSmsContextOrder;
import cc.openkit.admin.model.smsTempDo;
import cc.openkit.admin.service.g.GGroupLimitService;
import cc.openkit.admin.service.message.rytx.PhoneHelper;
import cc.openkit.admin.service.smstemp.SmsTempService;
import cc.openkit.admin.util.StaticFinalVar;
import cc.openkit.common.KitUtil;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

@Controller
@Scope("prototype")
@RequestMapping("/smsTemp")
public class SmsTempController {


    @Resource
    private SmsTempService smsTempService;

    @Resource
    private GGroupLimitService gGroupLimitService;

    private Logger log = Logger.getLogger(SmsTempController.class);

    /**
     * 获取短信模板页面
     *
     * @return
     */
    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    @ResponseBody
    public ModelAndView getSmsTempList(HttpServletRequest request, HttpServletResponse response) {

        log.info("去首次短信模板列表页面");

        // 权限验证
        GGroupLimit gGroupLimit = gGroupLimitService.testGroup(request, 27);
        ModelAndView mv = new ModelAndView();
        // 没有权限，返回错误页面
        if (gGroupLimit == null) {
            mv.setViewName("/admin/err/no_group_err");
            mv.addObject("msg", StaticFinalVar.NO_GROUP_MSG + StaticFinalVar.CALL_GROUP);
            return mv;
        }
        // 取值
        mv.addObject("kitG", gGroupLimit);
        mv.setViewName("/admin/smstemp/list");
        return mv;
    }


    /**
     * 获取列表数据
     *
     * @return
     */
    @RequestMapping(value = "/getAllJson", method = RequestMethod.POST)
    @ResponseBody
    public Object getAllJson(HttpServletRequest request) {
        log.info("获取短信模板列表的数据");

        Map<String, Object> map = new HashMap<String, Object>();

        String page = request.getParameter("page");// 获得页数
        String limit = request.getParameter("limit");// 获得每页显示条数
        String search = request.getParameter("search");// 获取搜索条件

        //        封装数据
        smsTempDo smsTempDo = new smsTempDo();
        if (KitUtil.feikong(search)) {
            smsTempDo.setTempId(search);
        }
        //短信模板类型 短信类型
        smsTempDo.setPreciseType(0);

        // 分页查询
        List<smsTempDo> list = smsTempService.queryPageListByWhere(smsTempDo, Integer.valueOf(page), Integer.valueOf(limit));
        int size = smsTempService.queryCount(smsTempDo);

        // 返回数据
        map.put("code", 0);
        map.put("msg", "操作成功");
        map.put("count", size);
        map.put("data", list);

        return JSONObject.toJSON(map);

    }


    /**
     * 修改前的查询
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/goUpdate", method = RequestMethod.GET)
    public ModelAndView goUpdate(HttpServletRequest request) throws Exception {
        String id = request.getParameter("id");
        smsTempDo smsTempDo = smsTempService.queryByUUID(id);
        ModelAndView mv = new ModelAndView();
        mv.setViewName("/admin/smstemp/update");
        mv.addObject("kitModel", smsTempDo);
        return mv;
    }


    /**
     * 发送短信
     *
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/sendSms", method = RequestMethod.POST)
    @ResponseBody
    public Object update(HttpServletRequest request) throws Exception {

        // 取值
        String phonesParam = request.getParameter("phones");
        String valuesParam = request.getParameter("values");
        String tempId = request.getParameter("tempId");


        if (StringUtils.isBlank(phonesParam) || StringUtils.isBlank(valuesParam)) {
            return JSONObject.toJSON(KitUtil.returnMap("101", "请输入正确的参数"));
        }
        String[] phones = phonesParam.split(",");
        String[] values = valuesParam.split(",");

        if (null == phones || null == values || phones.length == 0 || values.length == 0) {
            return JSONObject.toJSON(KitUtil.returnMap("101", "参数转换失败"));
        }
        //后台验证手机号码格式
        for (String phone : phones) {
            if (StringUtils.isBlank(phone)) {
                return JSONObject.toJSON(KitUtil.returnMap("101", "手机号码有误"));
            }
            if (phone.length() != 11) {
                return JSONObject.toJSON(KitUtil.returnMap("101", "手机号码有误"));
            }
            System.out.println(phone);
        }
        PhoneHelper helper = new PhoneHelper();
        YtxSmsContextOrder smsContext = new YtxSmsContextOrder();
        smsContext.setContents(values);
        smsContext.setSmsTempid(tempId);
        smsContext.setReceiverPhone(phonesParam.trim());
        boolean flag = helper.sendSmsByYTX(KitUtil.uuid(), smsContext);

        if (flag) {
            return JSONObject.toJSON(KitUtil.returnMap("200", "发送成功"));
        }
        return JSONObject.toJSON(KitUtil.returnMap("101", "发送失败"));
        // 封装对象
//        if (KitUtil.feikong(ahSummary)) {
//            advHello.setAhSummary(ahSummary);
//        }
//        return JSONObject.toJSON(advHelloService.updateSelective(advHello) == 1 ? KitUtil.returnMap("200", StaticFinalVar.UPDATE_OK) : KitUtil.returnMap("101", StaticFinalVar.UPDATE_ERR));
    }


}
