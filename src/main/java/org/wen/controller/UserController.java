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
        Result result = userService.regUser(name,pwd);
        log.info("<提示>"+result.toString());
        model.addAttribute("result",result);
        return result;
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
}
