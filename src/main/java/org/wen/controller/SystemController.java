package org.wen.controller;
import org.springframework.stereotype.Controller;
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
    @RequestMapping("/yhgl")
    @SystemControllerLog(description = "进入用户管理页面")
    public String index(){
        return "admin/yhgl";
    }
    @RequestMapping("/log")
    @SystemControllerLog(description = "进入日志管理页面")
    public String log(){
        return "admin/log";
    }
    @RequestMapping("/role")
    @SystemControllerLog(description = "进入角色管理页面")
    public String role(){
        return "admin/role";
    }
    @RequestMapping("/operation")
    @SystemControllerLog(description = "进入操作日志管理页面")
    public String operation(){return "admin/operation";}
    @RequestMapping("/menu")
    @SystemControllerLog(description = "进入菜单管理页面")
    public String menu(){return "admin/menu";}
}
