package cc.openkit.admin.dao;

import cc.openkit.admin.model.Admin;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface AdminMapper extends Mapper<Admin>{
    List<Admin> getModelByUsernameAndPassword(Admin admin);

    int updateByAdminId(Admin admin);
}