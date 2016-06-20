package org.wen.dao;

import org.wen.entity.User;

import java.io.Serializable;

/**
 * 用户模块对应的DAO方法
 */
public interface UserDao {
    public int reg(User user);
}
