package cc.openkit.admin.service.admin;

import cc.openkit.admin.model.Admin;
import cc.openkit.admin.service.common.BaseService;

public interface AdminService extends BaseService<Admin>{

    Admin getModelByUsernameAndPassword(Admin admin);

    int updateByAdminId(Admin admin);
}
