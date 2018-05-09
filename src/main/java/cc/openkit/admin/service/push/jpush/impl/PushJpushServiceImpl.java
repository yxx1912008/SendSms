package cc.openkit.admin.service.push.jpush.impl;

import cc.openkit.admin.dao.PushJpushMapper;
import cc.openkit.admin.model.PushJpush;
import cc.openkit.admin.service.common.impl.BaseServiceImpl;
import cc.openkit.admin.service.push.jpush.PushJpushService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
public class PushJpushServiceImpl extends BaseServiceImpl<PushJpush> implements PushJpushService {
    private Logger log = Logger.getLogger(PushJpushServiceImpl.class);

    @Resource
    private PushJpushMapper pushJpushMapper;
}
