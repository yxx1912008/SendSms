package cc.openkit.admin.service.pay.paySettingAlipay.impl;

import cc.openkit.admin.dao.PaySettingAlipayMapper;
import cc.openkit.admin.model.PaySettingAlipay;
import cc.openkit.admin.service.common.impl.BaseServiceImpl;
import cc.openkit.admin.service.pay.paySettingAlipay.PaySettingAlipayService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
public class PaySettingAlipayServiceImpl extends BaseServiceImpl<PaySettingAlipay> implements PaySettingAlipayService {
    private Logger log = Logger.getLogger(PaySettingAlipayServiceImpl.class);

    @Resource
    private PaySettingAlipayMapper paySettingAlipayMapper;
}
