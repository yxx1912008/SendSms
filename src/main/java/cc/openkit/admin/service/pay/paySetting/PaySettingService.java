package cc.openkit.admin.service.pay.paySetting;

import cc.openkit.admin.model.PaySetting;
import cc.openkit.admin.model.util.KitPay;
import cc.openkit.admin.service.common.BaseService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface PaySettingService extends BaseService<PaySetting> {
    public Map<String, Object> kitAppPayUnifyAPI(HttpServletRequest request, HttpServletResponse response, KitPay payUtil) throws Exception;
}
