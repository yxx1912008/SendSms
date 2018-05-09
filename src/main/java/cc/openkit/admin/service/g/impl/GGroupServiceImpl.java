package cc.openkit.admin.service.g.impl;

import cc.openkit.admin.dao.GGroupMapper;
import cc.openkit.admin.model.GGroup;
import cc.openkit.admin.service.common.impl.BaseServiceImpl;
import cc.openkit.admin.service.g.GGroupService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class GGroupServiceImpl  extends BaseServiceImpl<GGroup> implements GGroupService {
    private Logger log = Logger.getLogger(GGroupServiceImpl.class);

    @Resource
    private GGroupMapper gGroupMapper;

    public List<GGroup> getListByGroupName(GGroup gGroup) {
        return gGroupMapper.getListByGroupName(gGroup);
    }
}
