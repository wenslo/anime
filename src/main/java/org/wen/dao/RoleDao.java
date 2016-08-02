package org.wen.dao;

import org.wen.entity.Role;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 角色管理的DAO层，同时处理角色表和角色-用户中间表的数据
 * 2016年7月27日16:19:16
 * @author 温海林
 */
public interface RoleDao {
    /**
     * 查询角色管理页面所需数据
     * @param map 查询条件
     * @return 角色信息列表
     */
     List<Role> find(Map map);

    /**
     * 查询总数
     * @param map 查询条件
     * @return 角色总数
     */
     Long count(Map map);

    /**
     * 保存角色
     * @param role 角色信息
     * @return 保存后角色的ID
     */
     int save(Role role);

    /**
     * 根据ID进行删除
     * @param list ID的集合
     * @return 删除角色的总数
     */
     int deleteById(ArrayList<Long> list);

    /**
     * 查询角色表的所有信息
     * @return 所有角色
     */
     List<Role> findAll();

    /**
     * 根据传入的ID对角色表进行搜索。
     * @param list ID集合
     * @return 集合里ID的角色
     */
     List<Role> findByIds(ArrayList<Long> list);

    /**
     * 向角色与用户的中间表插入数据
     * @param map 用户数据
     * @return 插入结果
     */
     int addMis(Map<String, Integer> map);

    /**
     * 根据传入的用户ID，删除中间表中有关该用户的信息
     * @return 删除条数
     */
     int deleteByUserId(ArrayList<Long> list);

    /**
     * 根据传入的用户ID，找到对应的角色，然后找到比这个角色小的所有用户的Id
     * @return 该用户对应的角色ID
     */
     List<Integer> findLessUsers(Integer userId);

    /**
     * 查找角色数字，根据传入的数字与数据库中进行对比
     * @param roleNumber 角色数组
     * @return 是否存在
     */
    int findRoleNumber(Long roleNumber);

    /**
     * 查找比该角色数字大的记录
     * @param roleNumber 橘色数字
     * @return 有的话返回大于0的数字，没有的话小于0
     */
    int checkNumber(Long roleNumber);
}
