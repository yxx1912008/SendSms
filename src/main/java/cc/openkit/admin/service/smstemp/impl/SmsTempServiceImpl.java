package cc.openkit.admin.service.smstemp.impl;

import cc.openkit.admin.model.smsTempDo;
import cc.openkit.admin.service.common.impl.BaseServiceImpl;
import cc.openkit.admin.service.smstemp.SmsTempService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

import cc.openkit.admin.dao.smsTempMapper;

@Service
@Transactional(rollbackFor = Exception.class)
public class SmsTempServiceImpl extends BaseServiceImpl<smsTempDo> implements SmsTempService {

    private Logger log = Logger.getLogger(SmsTempServiceImpl.class);

    @Resource
    private smsTempMapper smsTempMapper;


}
