package cc.openkit.admin.service.pay.paySettingWeixin.impl;

import cc.openkit.admin.dao.PaySettingWeixinMapper;
import cc.openkit.admin.model.PaySettingWeixin;
import cc.openkit.admin.service.common.impl.BaseServiceImpl;
import cc.openkit.admin.service.pay.paySettingWeixin.PaySettingWeixinService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
public class PaySettingWeixinServiceImpl extends BaseServiceImpl<PaySettingWeixin> implements PaySettingWeixinService {
    private Logger log = Logger.getLogger(PaySettingWeixinServiceImpl.class);

    @Resource
    private PaySettingWeixinMapper paySettingWeixinMapper;
}
