package org.wen.dao;

import org.wen.entity.Log;
import org.wen.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * Created by wen on 2016/7/19.
 */
public interface RoleDao {
    /**
     * 查询用户管理页面所需数据
     *
     * @param map
     * @return
     */
    public List<Log> find(Map map);

    /**
     * 查询总数
     *
     * @param map
     * @return
     */
    public Long count(Map map);
}
