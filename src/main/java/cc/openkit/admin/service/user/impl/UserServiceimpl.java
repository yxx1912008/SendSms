package cc.openkit.admin.service.user.impl;

import cc.openkit.admin.dao.UserMapper;
import cc.openkit.admin.model.User;
import cc.openkit.admin.service.common.impl.BaseServiceImpl;
import cc.openkit.admin.service.message.impl.MessageServiceImpl;
import cc.openkit.admin.service.user.UserService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceimpl  extends BaseServiceImpl<User> implements UserService {

    private Logger log = Logger.getLogger(MessageServiceImpl.class);

    @Resource
    private UserMapper userMapper;
}
