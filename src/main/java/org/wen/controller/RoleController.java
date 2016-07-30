package org.wen.controller;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wen.dto.Result;
import org.wen.entity.DataGrid;
import org.wen.entity.Role;
import org.wen.section.SystemControllerLog;
import org.wen.service.RoleService;
import org.wen.util.ExcelUtil;
import javax.servlet.http.HttpServletResponse;
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
@RequestMapping("/role")
public class RoleController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private RoleService roleService;
    @RequestMapping(value = "/datagrid",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @SystemControllerLog(description = "角色列表")
    public DataGrid datagrid(String common,int page,int rows){
        return roleService.datagrid(common,page,rows);
    }
    @RequestMapping("/add")
    @ResponseBody
    @SystemControllerLog(description = "角色添加")
    public Result addRole(String roleName,String roleNumber) throws Exception {
        return roleService.addRole( roleNumber, roleName);
    }
    /**
     * 删除角色
     * 2016年7月20日00:06:33
     * 温海林
     * @param ids id集
     * @return 返回删除结果
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @SystemControllerLog(description = "角色删除")
    public Result delete(String ids){
        return roleService.deleteRole(ids);
    }

    /**
     * 得到所有角色
     * 2016年7月20日22:10:34
     * 温海林
     * @return 返回查询到的所有角色
     */
    @RequestMapping("getRole")
    @ResponseBody
    @SystemControllerLog(description = "查询角色")
    public List<Role> getRole(){
        List<Role> list = roleService.getRole();
        log.debug("数据测试：{}",list.toString());
        return list;
    }
    /**
     * Excel的导出
     * 2016年7月11日21:25:41
     * 温海林
     * @param res 相应结果
     * @throws IOException
     */
    @RequestMapping("/excelExport")
    @SystemControllerLog(description = "角色导出")
    public void excelExport(HttpServletResponse res,String ids) throws IOException {
        OutputStream os = res.getOutputStream();
        BufferedOutputStream bos;
        String sheetName = "角色信息";
        String head = "角色编号,角色名称";
        String key = "roleNumber,roleName";
        String[] headMsg = head.split(",");
        String[] keys = key.split(",");
        String fileName = "角色信息表";
        try {
            List<Map<String,Object>> maps = roleService.queryMap(ids);
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