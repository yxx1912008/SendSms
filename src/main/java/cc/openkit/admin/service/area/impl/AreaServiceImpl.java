package cc.openkit.admin.service.area.impl;

import cc.openkit.admin.dao.AreaMapper;
import cc.openkit.admin.model.Area;
import cc.openkit.admin.service.area.AreaService;
import cc.openkit.admin.service.common.impl.BaseServiceImpl;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
public class AreaServiceImpl extends BaseServiceImpl<Area> implements AreaService {

    private Logger log = Logger.getLogger(AreaServiceImpl.class);

    @Resource
    private AreaMapper areaMapper;

}
