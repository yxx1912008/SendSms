package cc.openkit.admin.dao;

import cc.openkit.admin.model.AdvHello;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface AdvHelloMapper extends Mapper<AdvHello> {

    List<AdvHello> appGet(Integer kitWebHellowAdvSize);
}