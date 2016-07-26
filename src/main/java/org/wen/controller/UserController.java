package org.wen.controller;

import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.sun.deploy.net.HttpResponse;
import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.wen.dto.Result;
import org.wen.entity.DataGrid;
import org.wen.entity.User;
import org.wen.section.SystemControllerLog;
import org.wen.service.RoleService;
import org.wen.service.UserService;
import org.wen.service.impl.UserServiceImpl;
import org.wen.util.ExcelUtil;
import org.wen.util.JsonUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * Created by wen on 2016/6/19.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;
    @Autowired
    private RoleService roleService;
    /**
     * 跳转到注册页面
     * @param model
     * @return
     */
    @RequestMapping("/index")
    @SystemControllerLog(description = "跳转到首页")
    public String index(Model model,HttpServletRequest request,HttpServletResponse response){
        return "index";
    }
    /**
     * 用户页面的注册功能
     * 2016年6月19日21:23:35
     * 温海林
     * @return
     */
    @RequestMapping(value = "/reg",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @SystemControllerLog(description = "用户注册")
    public Result reg(String name,String pwd,Model model){
        Result result = null;
        try {
            result = userService.regUser(name,pwd);
            log.info("<提示>"+result.toString());
            model.addAttribute("result",result);
            return result;
        }catch (Exception e){
            log.error("违反唯一约束！");
            result.setResult(2);
            result.setMessage("注册失败！");
            return result;
        }
    }

    /**
     * 用户页面的登录功能
     * 2016年6月22日20:55:17
     * 温海林
     * @param name  帐号
     * @param pwd 密码
     * @param model
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @SystemControllerLog(description = "用户登录")
    public Result log(String name,String pwd,Model model,HttpServletRequest request,HttpServletResponse response){
        Result result = userService.login(name,pwd);
        log.info("<提示>"+result.toString());
        model.addAttribute("result",result);
        if(result.getResult() == 1){
            User user = (User) result.getData();
            request.getSession().setAttribute("user",user);
        }
        log.info("Session数据测试：名字为{},密码为{}",name,pwd);
        return result;
    }
    @RequestMapping(value = "/datagrid",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @SystemControllerLog(description = "用户列表")
    public DataGrid datagrid(String name,int page,int rows,Model model){
        return userService.datagrid(name,page,rows);
    }
    @RequestMapping(value = "/add",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @SystemControllerLog(description = "用户新增")
    public Result add(String name,String pwd,Model model){
        Result result = new Result();
        try {
            result = userService.regUser(name,pwd);
            result.setMessage("新增用户成功！");
            log.info("<提示>"+result.toString());
            model.addAttribute("result",result);
            return result;
        }catch (Exception e){
            log.error("错误！",e);
            log.error("用户名已经存在！");
            result.setResult(2);
            result.setMessage("违反唯一约束！");
            return result;
        }
    }

    /**
     * 删除用户
     * 2016年7月2日18:20:53
     * 温海林
     * @param ids
     * @return
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @SystemControllerLog(description = "用户删除")
    public Result delete(String ids){
        Result result = userService.deleteUser(ids);
        return result;
    }
    /**
     * 显示出需要修改的用户
     * 2016年7月3日11:24:58
     * 温海林
     * @param id 需要修改的用户的ID
     * @return
     */
    @RequestMapping(value = "{id}/showUpdateUser",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @SystemControllerLog(description = "显示修改用户")
    public Result showUpdateUser(@PathVariable Long id,Model model){
        Result result = userService.findUser(id);
        return result;
    }
    /**
     * 修改用户
     * 2016年7月3日12:12:44
     * 温海林
     * @return
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @SystemControllerLog(description = "修改用户")
    public Result update(Long id,String name,String pwd,Model model){
        Result result = userService.updateUser(id,name,pwd);
        return result;
    }

    /**
     * Excel的导出
     * @param res
     * @throws IOException
     */
    @RequestMapping("/excelExport")
    @SystemControllerLog(description = "导出用户")
    public void excelExport(HttpServletResponse res,String ids) throws IOException {
        OutputStream os = res.getOutputStream();
        BufferedOutputStream bos = null;
        BufferedInputStream bis = null;
        String sheetName = "用户表";
        String head = "编号,用户名,密码,创建时间,修改时间";
        String key = "id,name,pwd,createDate,updateDate";
        String[] headMsg = head.split(",");
        String[] keys = key.split(",");
        String fileName = "用户表";
        try {
            List<Map<String,Object>> maps = userService.queryMap(ids);
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
    /**
     * Excel的导入
     * @throws IOException
     */
    @RequestMapping("/uploadExcel")
    @SystemControllerLog(description = "导入用户")
    public String doUploadFile(@RequestParam("uploadExcel") MultipartFile file) throws IOException {
        if(!file.isEmpty()){
            log.info("Process file(getOriginalFilename): {}", file.getOriginalFilename());
            log.info("Process file(getName): {}", file.getName());
            log.info("Process file(getContentType): {}", file.getContentType());
            log.info("Process file(getSize): {}", file.getSize());
//            FileUtils.copyInputStreamToFile(file.getInputStream(), new File("c:\\temp\\happyBKs\\", System.currentTimeMillis() + file.getOriginalFilename()));
        }
        //得到该文件的输入流
        InputStream input = file.getInputStream();
        List<User> users = ExcelUtil.uploadExcel(input);
        userService.addUsers(users);
        return "admin/yhgl";
    }
    @RequestMapping("/role")
    @ResponseBody
    @SystemControllerLog(description = "用户角色设置")
    public Result role(String userId,String roleId){
        Result result = roleService.addMis(userId,roleId);
        return result;
    }
}
