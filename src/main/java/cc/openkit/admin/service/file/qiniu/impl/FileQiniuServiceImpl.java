package cc.openkit.admin.service.file.qiniu.impl;

import cc.openkit.admin.dao.FileQiniuMapper;
import cc.openkit.admin.model.FileQiniu;
import cc.openkit.admin.service.common.impl.BaseServiceImpl;
import cc.openkit.admin.service.file.qiniu.FileQiniuService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
public class FileQiniuServiceImpl extends BaseServiceImpl<FileQiniu> implements FileQiniuService {

    private Logger log = Logger.getLogger(FileQiniuServiceImpl.class);

    @Resource
    private FileQiniuMapper fileQiniuMapper;

}
