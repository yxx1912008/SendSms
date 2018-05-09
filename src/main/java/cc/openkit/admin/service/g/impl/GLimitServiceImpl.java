package cc.openkit.admin.service.g.impl;

import cc.openkit.admin.dao.GLimitMapper;
import cc.openkit.admin.model.GLimit;
import cc.openkit.admin.service.common.impl.BaseServiceImpl;
import cc.openkit.admin.service.g.GLimitService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class GLimitServiceImpl  extends BaseServiceImpl<GLimit> implements GLimitService {
    private Logger log = Logger.getLogger(GLimitServiceImpl.class);

    @Resource
    private GLimitMapper gLimitMapper;

}
