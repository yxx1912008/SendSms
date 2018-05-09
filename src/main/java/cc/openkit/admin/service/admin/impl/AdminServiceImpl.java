package cc.openkit.admin.service.admin.impl;

import cc.openkit.admin.dao.AdminMapper;
import cc.openkit.admin.model.Admin;
import cc.openkit.admin.service.admin.AdminService;
import cc.openkit.admin.service.common.impl.BaseServiceImpl;
import cc.openkit.admin.util.AppUtil;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class AdminServiceImpl extends BaseServiceImpl<Admin> implements AdminService {

    private Logger log = Logger.getLogger(AdminServiceImpl.class);

    @Resource
    private AdminMapper adminMapper;

    public Admin getModelByUsernameAndPassword(Admin admin) {
        // 查询
//        密码加密
        admin.setKitAdminPassword(AppUtil.md5pwd(admin.getKitAdminPassword()));
        List<Admin> adminList = adminMapper.getModelByUsernameAndPassword(admin);
        Admin retuenAdmin = null;
        if (adminList.size() > 0) {
            retuenAdmin = adminList.get(0);
        }

        return retuenAdmin;
    }

    public int updateByAdminId(Admin admin) {
        return adminMapper.updateByAdminId(admin);
    }
}
