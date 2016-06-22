package org.wen.dao;

import org.wen.entity.User;

import java.io.Serializable;

/**
 * 用户模块对应的DAO方法
 */
public interface UserDao {
    /**
     * 用户注册方法
     * @param user
     * @return
     */
    public int reg(User user);

    /**
     * 用户登录方法
     * @param user
     */
    public User login(User user);
}
