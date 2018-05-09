package cc.openkit.admin.service.adv_hello;

import cc.openkit.admin.model.AdvHello;
import cc.openkit.admin.service.common.BaseService;

import java.util.List;

public interface AdvHelloService extends BaseService<AdvHello> {

    List<AdvHello> appGet(Integer kitWebHellowAdvSize);
}
