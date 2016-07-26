package org.wen.dao;

import org.wen.entity.Log;
import org.wen.entity.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 日志DAO
 */
public interface LogDao {
    /**
     * 查询用户管理页面所需数据
     * @param map
     * @return
     */
    public List<Log> find(Map map);

    /**
     * 查询总数
     * @param map
     * @return
     */
    public Long count(Map map);

    /**
     * 添加日志
     * @param log
     * @return
     */
    public int save(Log log);

    /**
     * 根据传入的ID删除日志
     * @param list
     * @return
     */
    public int deleteById(ArrayList<Long> list);

    /**
     * 查询所有数据
     * @return
     */
    public List<Log> findAll();

    /**
     * 根据所选ID，查询所对应的数据
     * @param list
     * @return
     */
    public List<Log> findByIds(List<Long> list);

    /**
     * 根据所选ID查询出对应的详情
     * @param id 日志ID
     * @return
     */
    public Log findById(int id);

    /**
     * 根据用户ID删除对应数据库中的文章
     * @param list
     * @return
     */
    public Integer deleteByUserId(List<Long> list);
}
