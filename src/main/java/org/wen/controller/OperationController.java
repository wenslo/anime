package org.wen.controller;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wen.entity.DataGrid;
import org.wen.section.SystemControllerLog;
import org.wen.service.OperationService;
import org.wen.util.ExcelUtil;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

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
    /**
     * Excel的导出
     * 2016年8月1日23:17:21
     * 温海林
     * @param res 相应结果
     * @throws IOException IO异常
     */
    @RequestMapping("/excelExport")
    @SystemControllerLog(description = "角色导出")
    public void excelExport(HttpServletResponse res) throws IOException {
        OutputStream os = res.getOutputStream();
        BufferedOutputStream bos;
        String sheetName = "角色信息";
        String head = "用户名,方法名称,方法描述,创建时间";
        String key = "userName,methodName,describeCommon,createDate";
        String[] headMsg = head.split(",");
        String[] keys = key.split(",");
        String fileName = "角色信息表";
        try {
            List<Map<String,Object>> maps = operationService.queryMap();
            res.setHeader("Content-disposition",
                    "attachment; filename="+java.net.URLEncoder.encode(fileName+".xls","UTF-8"));
            HSSFWorkbook workbook = ExcelUtil.export(sheetName, headMsg, keys, maps);
            bos = new BufferedOutputStream(os);
            workbook.write(bos);
            bos.flush();
            bos.close();
        } finally {
            if (os != null) {
                os.close();
            }
        }
    }
}
