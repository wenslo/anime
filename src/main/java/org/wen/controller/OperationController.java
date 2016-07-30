package org.wen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wen.entity.DataGrid;
import org.wen.section.SystemControllerLog;
import org.wen.service.OperationService;

/**
 * 操作日志Controller
 * 2016年7月30日00:00:20
 * @author 温海林
 */
@Controller
@RequestMapping("/operation")
public class OperationController {
    @Autowired
    private OperationService operationService;
    @RequestMapping(value = "/datagrid",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @SystemControllerLog(description = "角色列表")
    public DataGrid datagrid(String common,int page,int rows){
        return operationService.datagrid(page,rows);
    }
}
