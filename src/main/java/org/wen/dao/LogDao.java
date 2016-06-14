package org.wen.dao;

import org.wen.entity.Log;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 关于日志表的DAO层
 * 2016年6月6日21:38:40
 * 温海林
 */
public interface LogDao {
    /**
     * 需要有一个能够查找出所有的日志记录的方法
     * @return
     */
    List<Log> queryAll();
    /**
     * 需要有查看单独一个的方法，以便用于查看详情
     * @return
     */
    Log queryById(int id);
    /**
     * 需要有一个查询方法
     * @param username 姓名
     * @param common 内容
     * @param createDate 创建时间
     * @return
     */
    List<Log> queryByCondition(Map map);
    /**
     * 需要有个增加的方法
     * @param log
     * @return
     */
    void saveLog(Log log);
    /**
     * 徐璈一个修改的方法
     * @param id
     * @param log
     * @return
     */
    int updateLog(int id, Log log);
    /**
     * 需要有一个删除日志的方法
     * @param id
     * @return
     */
    int deleteLog(int id);
}
