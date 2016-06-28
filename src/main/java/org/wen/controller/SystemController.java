package org.wen.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 用于跳转左侧树列表的Controller
 * 2016年6月26日23:56:07
 * 温海林
 */
@Controller()
@RequestMapping("/system")
public class SystemController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @RequestMapping("/yhgl")
    public String index(Model model){
        log.info("<提示>:：跳转到用户管理页面");
        return "admin/yhgl";
    }
}
