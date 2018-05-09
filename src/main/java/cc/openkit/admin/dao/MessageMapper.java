package cc.openkit.admin.dao;


import cc.openkit.admin.model.Message;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

@Component
public interface MessageMapper  extends Mapper<Message> {


}