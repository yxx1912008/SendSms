package cc.openkit.admin.dao;

import cc.openkit.admin.model.Sign;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface SignMapper extends Mapper<Sign> {

    List<Sign> getModelByPhoneOrderByTimeEignt(@Param("phone") String phone,@Param("size") int size);

    List<Sign> getModelByKSTAndPhoneAndCode(Sign sign);
}