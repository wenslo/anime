package org.wen.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wen.section.SystemControllerLog;

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
    @SystemControllerLog(description = "用户管理页面")
    public String index(Model model){
        return "admin/yhgl";
    }
    @RequestMapping("/log")
    @SystemControllerLog(description = "日志管理页面")
    public String log(Model model){
        return "admin/log";
    }
    @RequestMapping("/role")
    @SystemControllerLog(description = "角色管理页面")
    public String role(Model model){
        return "admin/role";
    }
}
