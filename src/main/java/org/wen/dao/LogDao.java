package org.wen.dao;

import org.wen.entity.Log;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 日志DAO
 */
public interface LogDao {
    /**
     * 查询用户管理页面所需数据
     * @param map 查询数据，总数，页数，条件
     * @return 返回日志的list
     */
     List<Log> find(Map map);

    /**
     * 查询总数
     * @param map 查询条件
     * @return 日志总数
     */
     Long count(Map map);

    /**
     * 添加日志
     * @param log 添加的日志对象
     * @return 返回保存后的日志ID
     */
     int save(Log log);

    /**
     * 根据传入的ID删除日志
     * @param list 日志ID结果
     * @return 删除的日志的数目
     */
     int deleteById(ArrayList<Long> list);

    /**
     * 查询所有数据
     * @return 所有日志
     */
     List<Log> findAll();

    /**
     * 根据所选ID，查询所对应的数据
     * @param list 日志ID集合
     * @return 日志
     */
     List<Log> findByIds(List<Long> list);

    /**
     * 根据所选ID查询出对应的详情
     * @param id 日志ID
     * @return 日志
     */
     Log findById(int id);

    /**
     * 根据用户ID删除对应数据库中的文章
     * @param list ID集合
     * @return 删除条数
     */
     Integer deleteByUserId(List<Long> list);

    /**
     * 查询下属的日志
     * @param params 查询条件 ID集合
     * @return 下属的日志
     */
     List<Log> findOhter(Map<String, Object> params);

    /**
     * 查询下属用户日志的总数
     * @param params 查询条件 ID集合
     * @return 下属日志的总数
     */
     Long countOther(Map<String, Object> params);
}
