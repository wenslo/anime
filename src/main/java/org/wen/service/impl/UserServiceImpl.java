package org.wen.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
import java.sql.Timestamp;
import java.util.*;

/**
 * 用户模块的实现
 */
@Service
@Transactional
public class UserServiceImpl implements UserService {
    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource
    private UserDao userDao;
    public Result regUser(String name, String pwd) throws Exception{
        Result result = new Result();
        User user = new User(name,getMd5(pwd));
        user.setCreateDate(new Date());
        int flag  = userDao.reg(user);
        if(flag > 0){
            user.setId(flag);
            result.setResult(1);
            result.setMessage("注册成功！");
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
            result.setData(result);
            result.setData(user);
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
        ArrayList<Long> list = Lists.newArrayList();
        for(String number :id){
            list.add(Long.parseLong(number));
        }
        int count = userDao.deleteById(list);
        result.setMessage("删除成功！一共删除了"+count+"条数据");
        result.setResult(1);
        return result;
    }

    public Result findUser(Long id) {
        Result result = new Result();
        User user = userDao.findById(id);
        log.debug("数据测试",user.toString());
        result.setResult(1);
        result.setData(user);
        return result;
    }

    public Result updateUser(Long id, String name, String pwd) {
        Result result = new Result();
        try {
            User user = new User(name,getMd5(pwd));
            user.setId(Integer.parseInt(String.valueOf(id)));
            user.setUpdateDate(new Date());
            userDao.updateUser(user);
            result.setMessage("修改成功！");
            result.setData(user);
            result.setResult(1);
        }catch (Exception e){
            log.error("错误！",e);
            result.setMessage("修改失败！");
            result.setResult(2);
        }
        return result;
    }

    public List<Map<String, Object>> queryMap(String ids) {
        if("quanbu".equals(ids)){
            List<User> users = userDao.findAll();
            List<Map<String, Object>> original = dataWrite(users);
            return original;
        }
        String userId = ids.substring(0,ids.lastIndexOf(","));
        String[] id = ids.split(",");
        ArrayList<Long> list = Lists.newArrayList();
        for(String number :id){
            list.add(Long.parseLong(number));
        }
        List<User> users = userDao.findByIds(list);
        List<Map<String, Object>> original = dataWrite(users);
        return original;
    }

    /**
     * 用于Excel导出的数据封装
     * @param users
     * @return
     */
    public List<Map<String, Object>> dataWrite(List<User> users) {
        List<Map<String,Object>> original = new ArrayList<Map<String, Object>>();
        for(User u :users){
            Map<String,Object > map = Maps.newHashMap();
            map.put("id",u.getId());
            map.put("name",u.getName());
            map.put("pwd",u.getPwd());
            map.put("createDate",u.getCreateDate());
            map.put("updateDate",u.getUpdateDate());
            original.add(map);
        }
        return original;
    }

    public String getMd5(String base){
        String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
        return md5;
    }
}
