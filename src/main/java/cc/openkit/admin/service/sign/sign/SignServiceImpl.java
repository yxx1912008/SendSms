package cc.openkit.admin.service.sign.sign;

import cc.openkit.admin.dao.SignMapper;
import cc.openkit.admin.dao.WebSettingMapper;
import cc.openkit.admin.model.Sign;
import cc.openkit.admin.model.WebSetting;
import cc.openkit.admin.service.common.impl.BaseServiceImpl;
import cc.openkit.admin.service.sign.SignService;
import cc.openkit.admin.util.AppUtil;
import cc.openkit.admin.util.StaticFinalVar;
import cc.openkit.common.KitUtil;
import org.apache.commons.collections.map.HashedMap;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor = Exception.class)
public class SignServiceImpl extends BaseServiceImpl<Sign> implements SignService {
    private Logger log = Logger.getLogger(SignServiceImpl.class);

    @Resource
    private SignMapper signMapper;
    @Resource
    private WebSettingMapper webSettingMapper;

    /**
     * 发送验证码的时候的验证码，我们需要按照手机号码查找最细的8条的内容
     *
     * 1. 同一个手机号码 1 分钟只能发 1 条
     * 2. 同一个手机号码 1 小时只能发 4 条
     * 2. 同一个手机号码 24 小时只能发 8 条
     *
     * 如果想自己设置，可以在 StaticFinalVar 中自己修改常量
     *
     * @param phone
     * @return
     */
    public Map<String, Object> isOk(String phone) {

        // 根据手机号码 按照时间倒叙排序，找到这个手机号码最后发的 8 条短信
        List<Sign> signList = signMapper.getModelByPhoneOrderByTimeEignt(phone, StaticFinalVar.SIGN_ONE_DAY_SIZE);
        // 如果大于 1分钟内容只能发的 条数，比较，当前时间 和 第
        if(signList.size()>=StaticFinalVar.SIGN_ONE_MIN_SIZE){
            // 获取差
            int i = KitUtil.timeDifference(signList.get(StaticFinalVar.SIGN_ONE_MIN_SIZE - 1).getSignTime(), new Date(), 1);
            if (i<60){
                return KitUtil.returnMap("101","1分钟最多只能发送"+StaticFinalVar.SIGN_ONE_MIN_SIZE+"条");
            }
        }
        // 比较小时
        if(signList.size()>=StaticFinalVar.SIGN_ONE_HOUR_SIZE){
            // 获取差
            int i = KitUtil.timeDifference(signList.get(StaticFinalVar.SIGN_ONE_HOUR_SIZE - 1).getSignTime(), new Date(), 2);
            if (i<60){
                return KitUtil.returnMap("101","1小时最多只能发送"+StaticFinalVar.SIGN_ONE_HOUR_SIZE+"条");
            }
        }
        // 比较天
        if(signList.size()>=StaticFinalVar.SIGN_ONE_DAY_SIZE){
            // 获取差
            int i = KitUtil.timeDifference(signList.get(StaticFinalVar.SIGN_ONE_DAY_SIZE - 1).getSignTime(), new Date(), 3);
            if (i<24){
                return KitUtil.returnMap("101","24小时最多只能发送"+StaticFinalVar.SIGN_ONE_DAY_SIZE+"条");
            }
        }

        return KitUtil.returnMap("200","");
    }

    /**
     * 这里是验证码验证
     * @param phone 手机号码
     * @param kst 验证码唯一token
     * @param code 验证码
     * @return
     */

    public Map<String, Object> isSignOk(String phone,String kst,String code){
        Map<String, Object> map = new HashMap<String, Object>();
        // 判断非空
        Map<String, Object> isNullMap = new HashMap<String, Object>();
        isNullMap.put("phone",phone);
        isNullMap.put("kst",kst);
        isNullMap.put("code",code);

        map = KitUtil.isNull(isNullMap);

        if("300".equals(map.get("code"))){
            map.remove("code");
            map.put("code","101");
            return map;
        }

        // 封装对象
        Sign sign = new Sign();
        sign.setSignId(kst);
        sign.setSignPhone(phone);
        sign.setSignCode(code);

        List<Sign> signList = signMapper.getModelByKSTAndPhoneAndCode(sign);
        if(signList.size()==0){
            return KitUtil.returnMap("101","验证码无效");
        }
        Sign s = signList.get(0);
        // 判断是否失效
        if(s.getSignType()!=1){
            return KitUtil.returnMap("101","验证码失效");
        }
        // 获取验证码有效时间
        WebSetting webSetting = webSettingMapper.selectByPrimaryKey(1);
        int mins = KitUtil.timeDifference(s.getSignTime(), new Date(),2);
        if(mins > webSetting.getKitSignActiveTime()){
            return KitUtil.returnMap("101","验证码已超时");
        }
        // 修改验证码状态为已经验证
        s.setSignType(2);
        signMapper.updateByPrimaryKeySelective(s);
        return KitUtil.returnMap("200","");
    }

}
