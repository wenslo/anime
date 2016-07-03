package org.wen.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wen.dto.Result;
import org.wen.entity.DataGrid;
import org.wen.service.UserService;

/**
 * Created by wen on 2016/6/19.
 */
@Controller
@RequestMapping("/user")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private UserService userService;
    /**
     * 跳转到注册页面
     * @param model
     * @return
     */
    @RequestMapping("/index")
    public String index(Model model){
        log.info("<提示>:：跳转到首页");
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
    public Result reg(String name,String pwd,Model model){
        log.info("<提示>：进入注册方法");
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
    public Result log(String name,String pwd,Model model){
        log.info("<提示>：进入登录方法");
        Result result = userService.login(name,pwd);
        log.info("<提示>"+result.toString());
        model.addAttribute("result",result);
        return result;
    }
    @RequestMapping(value = "/datagrid",method = RequestMethod.POST,produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public DataGrid datagrid(String name,int page,int rows,Model model){
        log.info("<提示>：进入用户管理页面的方法");
        return userService.datagrid(name,page,rows);
    }
    @RequestMapping(value = "/add",produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public Result add(String name,String pwd,Model model){
        log.info("<提示>：进入新增用户方法");
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
    public Result delete(String ids){
        log.info("<提示>：进入删除用户方法");
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
    public Result showUpdateUser(@PathVariable Long id,Model model){
        log.info("<提示>：进入展示需要修改用户方法");
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
    public Result update(Long id,String name,String pwd,Model model){
        log.info("<提示>：进入展示需要修改用户方法");
        Result result = userService.updateUser(id,name,pwd);
        return result;
    }
}
