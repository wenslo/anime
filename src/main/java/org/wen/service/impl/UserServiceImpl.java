package org.wen.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.wen.dao.UserDao;
import org.wen.dto.Result;
import org.wen.entity.User;
import org.wen.service.UserService;

import javax.annotation.Resource;

/**
 * 用户模块的实现
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource
    private UserDao userDao;
    public Result regUser(String name, String pwd) {
        Result result = new Result();
        User user = new User(name,getMd5(pwd));
        try {
            int flag  = userDao.reg(user);
            if(flag > 0){
                result.setResult(1);
                result.setMessage("注册成功！");
                return result;
            }
        }catch (Exception e){
            log.error("违反唯一约束！");
            result.setResult(2);
            result.setMessage("注册失败！");
            return result;
        }

        return null;
    }

    public Result login(String name, String pwd) {
        Result result = new Result();
        User user = new User(name,getMd5(pwd));
        User flag = userDao.login(user);
        //flag > 0  说明数据库有该数据  则登录陈宫
        if(flag!=null){
            result.setResult(1);
            result.setMessage("登录成功");
        }else{
            result.setResult(2);
            result.setMessage("用户名或者密码不正确！");
        }
        return result;
    }

    public String getMd5(String base){
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
