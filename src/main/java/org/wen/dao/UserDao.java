package org.wen.dao;

import org.wen.dto.Table;
import org.wen.entity.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 用户模块对应的DAO方法
 */
public interface UserDao {
    /**
     * 用户注册方法
     * @param user 用户数据
     * @return 保存后的用户的ID
     */
     int reg(User user);

    /**
     * 用户登录方法
     * @param user 用户数据，验证账号密码
     * @return 用户
     */
     User login(User user);

    /**
     * 查询用户管理页面所需数据
     * @param map 用户查询条件
     * @return 查询出的用户结果
     */
     List<User> find(Map map);

    /**
     * 查询总数
     * @param map  查询条件
     * @return 符合条件的用户总数
     */
     Long count(Map map);

    /**
     * 根据页面传入的ID进行用户的删除
     * @param ids ID集合
     * @return 删除的数目
     */
     int deleteById(List<Long> ids);

    /**
     * 根据传入的ID寻找对应的用户
     * @param id 用户ID
     * @return 查询到的用户数据
     */
     User findById(Long id);

    /**
     * 修改用户
     * @param user 用户的数据
     * @return 修改结果
     */
     int updateUser(User user);

    /**
     * 根据多个ID查询数据
     * @param list ID的List
     * @return 用户列表
     */
    List<User> findByIds(ArrayList<Long> list);

    /**
     * 查询所有的用户
     * 2016年7月6日21:49:00
     * @return 所有用户
     */
    List<User> findAll();

    /**
     * 插入角色Id
     * @return 插入结果
     */
     int insertRole(Map<String,Integer> map);

    /**
     * 这个方法的含义是查询数据库中的表名和注释，然后包装成实体返回，不过适用于oracle
     * 使用这个方法的时候需要添加oracle的驱动包
     * @return 数据库表名与表注释
     */
     List<Table> findTable();
}
