package org.wen.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wen.entity.DataGrid;
import org.wen.service.LogService;
import org.wen.service.RoleService;

/**
 * 日志的Controller控制层
 * 2016年6月16日23:36:57
 * 温海林
 */
@Controller
@RequestMapping("/role")
public class RoleController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    RoleService roleService;
    @RequestMapping(value = "/datagrid",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public DataGrid datagrid(String common,int page,int rows,Model model){
        log.info("<提示>：进入日志管理页面");
        return roleService.datagrid(common,page,rows);
    }

}