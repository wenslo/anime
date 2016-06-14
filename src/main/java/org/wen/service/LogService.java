package org.wen.service;

import org.wen.entity.Log;

import java.util.Date;
import java.util.List;

/**
 * 日志service接口定义
 * 2016年6月14日22:02:09
 * 温海林
 */
public interface LogService {
    /**
     * 查询出所有日志
     * 后面加上分页
     * @return
     */
    List<Log> queryAll();
    /**
     * 查看详情，根据ID查找出对应的内容以便于观看
     * @return
     */
    Log queryOne();
    /**
     * 搜索日志的方法
     * @param username
     * @param common
     * @param createDate
     * @return
     */
    List<Log> queryByCondition(String username, String common, Date createDate);
    /**
     * 需要有一个新增日志的方法
     * @param log
     */
    int saveLog(Log log);
    /**
     * 修改日志
     * @param log  需要修改的实体类
     * @return
     */
    int updateLog(Log log);
}
