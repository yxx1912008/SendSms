package cc.openkit.admin.service.web.impl;

import cc.openkit.admin.dao.WebSettingMapper;
import cc.openkit.admin.model.WebSetting;
import cc.openkit.admin.service.common.impl.BaseServiceImpl;
import cc.openkit.admin.service.web.WebSettingService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
public class WebSettingServiceImpl extends BaseServiceImpl<WebSetting> implements WebSettingService {
    private Logger log = Logger.getLogger(WebSettingServiceImpl.class);

    @Resource
    private WebSettingMapper webSettingMapper;
}
