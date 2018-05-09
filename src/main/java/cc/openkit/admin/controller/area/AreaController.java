package cc.openkit.admin.controller.area;

import cc.openkit.admin.service.area.AreaService;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 地区表
 */
@Controller
@Scope("prototype")
@RequestMapping("/area")
public class AreaController {
    private Logger log = Logger.getLogger(AreaController.class);

    @Resource
    private AreaService areaService;
}
