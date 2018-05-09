package cc.openkit.admin.service.adv_hello.impl;

import cc.openkit.admin.dao.AdvHelloMapper;
import cc.openkit.admin.model.AdvHello;
import cc.openkit.admin.service.adv_hello.AdvHelloService;
import cc.openkit.admin.service.common.impl.BaseServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdvHelloServiceImpl extends BaseServiceImpl<AdvHello> implements AdvHelloService{

    private Logger log = Logger.getLogger(AdvHelloServiceImpl.class);

    @Resource
    private AdvHelloMapper advHelloMapper;

    public List<AdvHello> appGet(Integer kitWebHellowAdvSize) {
        return advHelloMapper.appGet(kitWebHellowAdvSize);
    }
}
