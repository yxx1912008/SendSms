package cc.openkit.admin.service.g;

import cc.openkit.admin.model.GGroupLimit;
import cc.openkit.admin.service.common.BaseService;
import cc.openkit.admin.vo.LimitVo;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface GGroupLimitService  extends BaseService<GGroupLimit> {
    List<LimitVo> getModelByGroupId(Integer groupId);

    GGroupLimit getModelByGroupIdAndLimitId(int adminid, int id);

    GGroupLimit testGroup(HttpServletRequest request, int i);

    void delByGroupId(Integer integer);

    List<GGroupLimit> getListByGroupId(Integer integer);
}
