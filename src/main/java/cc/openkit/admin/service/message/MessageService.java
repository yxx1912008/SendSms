package cc.openkit.admin.service.message;

import cc.openkit.admin.model.Message;
import cc.openkit.admin.model.util.KitMessage;
import cc.openkit.admin.service.common.BaseService;

import java.util.Map;

public interface MessageService extends BaseService<Message> {
    Map<String,Object> send(KitMessage kitMessage);




}
