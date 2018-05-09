package cc.openkit.admin.dao;

import cc.openkit.admin.model.GGroupLimit;
import cc.openkit.admin.vo.LimitVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

@Component
public interface GGroupLimitMapper extends Mapper<GGroupLimit> {

    List<LimitVo> getModelByGroupId(@Param("groupId") Integer groupId);

    List<GGroupLimit>  getModelByGroupIdAndLimitId(@Param("adminid")int adminid,@Param("id")int id);

    void delByGroupId(Integer integer);

    List<GGroupLimit> getListByGroupId(Integer integer);
}