package org.wen.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;
import org.wen.dao.UserDao;
import org.wen.dto.Result;
import org.wen.entity.DataGrid;
import org.wen.entity.User;
import org.wen.service.UserService;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        int flag  = userDao.reg(user);
        if(flag > 0){
            User customer = new User();
            customer.setName(name);
            customer.setPwd(getMd5(pwd));
            customer.setId(flag);
            result.setResult(1);
            result.setMessage("注册成功！");
            result.setData(customer);
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

    public DataGrid datagrid(String name,int page,int rows) {
        DataGrid dg = new DataGrid();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("name",name);
        params.put("page",page-1);
        params.put("rows",rows);
        List<User> l = userDao.find(params);
        dg.setTotal(userDao.count(params));
        dg.setRows(l);
        return dg;
    }

    public Result deleteUser(String ids) {
        Result result = new Result();
        String[] id = ids.split(",");
        ArrayList<Long> list = new ArrayList<Long>();
        for(String number :id){
            list.add(Long.parseLong(number));
        }
        int count = userDao.deleteById(list);
        result.setMessage("删除成功！一共删除了"+count+"条数据");
        result.setResult(1);
        return result;
    }

    public String getMd5(String base){
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
