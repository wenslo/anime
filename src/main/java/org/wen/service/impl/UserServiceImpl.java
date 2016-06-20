package org.wen.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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
    @Resource
    private UserDao userDao;
    public Result regUser(String name, String pwd) {
        Result result = new Result();
        User user = new User(name,pwd);
        user.setName(name);
        user.setPwd(pwd);
        int flag  = userDao.reg(user);
        if(flag > 0){
            result.setResult(1);
            result.setMessage("注册成功！");
            return result;
        }
        return null;
    }
}
