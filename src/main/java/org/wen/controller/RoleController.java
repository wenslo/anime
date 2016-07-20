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
import org.wen.entity.Role;
import org.wen.service.LogService;
import org.wen.service.RoleService;
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
    @RequestMapping("/add")
    @ResponseBody
    public Result addRole(HttpServletRequest request,HttpServletResponse response,String roleName,String roleNumber) throws Exception {
        String name = (String) request.getSession().getAttribute("name");
        log.info(name+"进入角色模块，添加方法");
        Result result = roleService.addRole( roleNumber, roleName);
        return result;
    }
    /**
     * 删除角色
     * 2016年7月20日00:06:33
     * 温海林
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result delete(String ids){
        log.info("<提示>：进入删除角色方法");
        Result result = roleService.deleteRole(ids);
        return result;
    }

    /**
     * 得到所有角色
     * 2016年7月20日22:10:34
     * 温海林
     * @return
     */
    @RequestMapping("getRole")
    @ResponseBody
    public List<Role> getRole(){
        log.info("查询对应的项目名和ID");
        List<Role> list = roleService.getRole();
        log.debug("数据测试：{}",list.toString());
        return list;
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