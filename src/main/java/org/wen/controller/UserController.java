package org.wen.controller;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.wen.dto.Result;
import org.wen.entity.DataGrid;
import org.wen.entity.User;
import org.wen.section.SystemControllerLog;
import org.wen.service.RoleService;
import org.wen.service.UserService;
import org.wen.util.ExcelUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;

/**
 * 用户管理的Controller配置，包含用户登录和注册
 * 2016年7月27日15:58:18
 * @author 温海林
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
     * @return 返回首页
     */
    @RequestMapping("/index")
    @SystemControllerLog(description = "跳转到首页")
    public String index(){
        return "index";
    }
    /**
     * 用户页面的注册功能
     * 2016年6月19日21:23:35
     * 温海林
     * @return 返回注册结果，同时返回session数据
     */
    @RequestMapping(value = "/reg",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @SystemControllerLog(description = "用户注册")
    public Result reg(String name,String pwd,Model model){
        Result result = new Result();
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
     * @param model session保存的数据
     * @return 返回用户登录成功的判断结果
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @SystemControllerLog(description = "用户登录")
    public Result log(String name,String pwd,Model model,HttpServletRequest request){
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
    public DataGrid datagrid(String name,int page,int rows){
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
     * @param ids id集
     * @return 返回删除结果
     */
    @RequestMapping(value = "/delete",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @SystemControllerLog(description = "用户删除")
    public Result delete(String ids){
        return userService.deleteUser(ids);
    }
    /**
     * 显示出需要修改的用户
     * 2016年7月3日11:24:58
     * 温海林
     * @param id 需要修改的用户的ID
     * @return 返回修改后的结果
     */
    @RequestMapping(value = "{id}/showUpdateUser",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @SystemControllerLog(description = "显示修改用户")
    public Result showUpdateUser(@PathVariable Long id){
        return userService.findUser(id);
    }
    /**
     * 修改用户
     * 2016年7月3日12:12:44
     * 温海林
     * @return 返回修改后的用户
     */
    @RequestMapping(value = "/update",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    @SystemControllerLog(description = "修改用户")
    public Result update(Long id,String name,String pwd){
        return userService.updateUser(id,name,pwd);
    }

    /**
     * Excel的导出
     * @param res 响应输出流
     * @throws IOException
     */
    @RequestMapping("/excelExport")
    @SystemControllerLog(description = "导出用户")
    public void excelExport(HttpServletResponse res,String ids) throws IOException {
        OutputStream os = res.getOutputStream();
        BufferedOutputStream bos;
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
        return roleService.addMis(userId,roleId);
    }
}
