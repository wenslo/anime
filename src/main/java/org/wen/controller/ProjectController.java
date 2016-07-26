package org.wen.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wen.entity.Project;
import org.wen.section.SystemControllerLog;
import org.wen.service.ProjectService;

import java.util.List;

/**
 * 项目的控制器
 * 2016年7月9日20:08:33
 * 温海林
 */
@Controller
@RequestMapping("/project")
public class ProjectController {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private ProjectService projectService;
    @RequestMapping("/getProject")
    @ResponseBody
    @SystemControllerLog(description = "项目查询")
    public List<Project> getProject(Model model){
        log.info("查询对应的项目名和ID");
        List<Project> list = projectService.getProject();
        log.debug("数据测试：{}",list.toString());
        return list;
    }
}
