package cc.openkit.admin.dao;

import cc.openkit.admin.model.UserLoginSetting;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component
public interface UserLoginSettingMapper extends Mapper<UserLoginSetting> {

}