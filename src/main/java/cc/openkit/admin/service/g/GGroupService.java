package cc.openkit.admin.service.g;

import cc.openkit.admin.model.GGroup;
import cc.openkit.admin.service.common.BaseService;

import java.util.List;

public interface GGroupService  extends BaseService<GGroup> {
    List<GGroup> getListByGroupName(GGroup gGroup);
}
