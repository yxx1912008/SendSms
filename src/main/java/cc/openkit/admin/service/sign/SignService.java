package cc.openkit.admin.service.sign;

import cc.openkit.admin.model.Sign;
import cc.openkit.admin.service.common.BaseService;

import java.util.Map;

public interface SignService extends BaseService<Sign> {
    Map<String, Object> isOk(String phone);
    public Map<String, Object> isSignOk(String phone,String kst,String code);
}
