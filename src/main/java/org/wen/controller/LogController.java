package org.wen.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.wen.entity.Log;
import org.wen.service.LogService;

import java.util.List;

/**
 * 日志的Controller控制层
 * 2016年6月16日23:36:57
 * 温海林
 */
@Controller
@RequestMapping("/log")
public class LogController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LogService logService;
    @RequestMapping(value = "list")
    public String list(Model model){
        //获取列表页
        log.info("<提示>:：==================================================={}进入list方法","lsit");
        List<Log> list = logService.queryAll();
        model.addAttribute("list",list);
        return "index";
    }

}
