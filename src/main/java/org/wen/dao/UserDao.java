package org.wen.dao;

import org.wen.entity.User;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户模块对应的DAO方法
 */
public interface UserDao {
    /**
     * 用户注册方法
     * @param user
     * @return
     */
    public int reg(User user) throws Exception;

    /**
     * 用户登录方法
     * @param user
     */
    public User login(User user);

    /**
     * 查询用户管理页面所需数据
     * @param map
     * @return
     */
    public List<User> find(Map map);

    /**
     * 查询总数
     * @param map
     * @return
     */
    public Long count(Map map);

    /**
     * 根据页面传入的ID进行用户的删除
     * @param ids
     * @return
     */
    public int deleteById(List<Long> ids);

    /**
     * 根据传入的ID寻找对应的用户
     * @param id
     * @return
     */
    public User findById(Long id);

    /**
     * 修改用户
     * @param user
     * @return
     */
    public int updateUser(User user);

    /**
     * 根据多个ID查询数据
     * @param list ID的List
     * @return
     */
    List<User> findByIds(ArrayList<Long> list);

    /**
     * 查询所有的用户
     * 2016年7月6日21:49:00
     * @return
     */
    List<User> findAll();
}
