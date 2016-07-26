package org.wen.dao;

import org.wen.entity.Log;
import org.wen.entity.Role;
import org.wen.entity.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wen on 2016/7/19.
 */
public interface RoleDao {
    /**
     * 查询角色管理页面所需数据
     *
     * @param map
     * @return
     */
    public List<Role> find(Map map);

    /**
     * 查询总数
     *
     * @param map
     * @return
     */
    public Long count(Map map);

    /**
     * 保存角色
     * @param role
     * @return
     */
    public int save(Role role);

    /**
     * 根据ID进行删除
     * @param list
     * @return
     */
    public int deleteById(ArrayList<Long> list);

    /**
     * 查询角色表的所有信息
     * @return
     */
    public List<Role> findAll();

    /**
     * 根据传入的ID对角色表进行搜索。
     * @param list
     * @return
     */
    public List<Role> findByIds(ArrayList<Long> list);

    /**
     * 向角色与用户的中间表插入数据
     * @param map
     * @return
     */
    public int addMis(Map<String, Integer> map);

    /**
     * 根据传入的用户ID，删除中间表中有关该用户的信息
     * @return
     */
    public int deleteByUserId(ArrayList<Long> list);
}
