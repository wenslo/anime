package org.wen.controller;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wen.dto.Result;
import org.wen.entity.DataGrid;
import org.wen.entity.Log;
import org.wen.entity.Project;
import org.wen.service.LogService;
import org.wen.util.ExcelUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

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
    @RequestMapping(value = "/datagrid",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public DataGrid datagrid(String common,int page,int rows,Model model){
        log.info("<提示>：进入日志管理页面");
        return logService.datagrid(common,page,rows);
    }
    @RequestMapping("/add")
    @ResponseBody
    public Result addLog(HttpServletRequest request,HttpServletResponse response,String projectName,String common,String createDate) throws Exception {
        String name = (String) request.getSession().getAttribute("name");
        log.info(name+"进入日志模块，添加方法");
        Result result = logService.addLog(name,projectName,common,createDate);
        return result;
    }
    /**
     * 删除日志
     * 2016年7月10日23:38:12
     * 温海林
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result delete(String ids){
        log.info("<提示>：进入删除用户方法");
        Result result = logService.deleteLog(ids);
        return result;
    }
    /**
     * Excel的导出
     * 2016年7月11日21:25:41
     * 温海林
     * @param res
     * @throws IOException
     */
    @RequestMapping("/excelExport")
    public void excelExport(HttpServletResponse res,String ids) throws IOException {
        OutputStream os = res.getOutputStream();
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        String sheetName = "日志记录";
        String head = "姓名,内容,所属项目,创建时间,修改时间,完成状态";
        String key = "name,common,projectName,createDate,updateDate,state";
        String[] headMsg = head.split(",");
        String[] keys = key.split(",");
        String fileName = "日志记录表";
        try {
            List<Map<String,Object>> maps = logService.queryMap(ids);
            res.setHeader("Content-disposition",
                    "attachment; filename="+java.net.URLEncoder.encode(fileName+".xls","UTF-8"));
            HSSFWorkbook workbook = ExcelUtil.export(sheetName, headMsg, keys, maps, fileName);
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
