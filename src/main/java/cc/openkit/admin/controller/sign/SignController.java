package cc.openkit.admin.controller.sign;

import cc.openkit.admin.service.g.GGroupLimitService;
import cc.openkit.admin.service.sign.SignService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@Controller
@Scope("prototype")
@RequestMapping("/sign")
public class SignController {
    private Logger log = Logger.getLogger(SignController.class);

    @Resource
    private SignService signService;
    @Resource
    private GGroupLimitService gGroupLimitService;
}
