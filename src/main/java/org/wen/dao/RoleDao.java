package org.wen.dao;

import org.wen.entity.Log;
import org.wen.entity.Role;

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
}
