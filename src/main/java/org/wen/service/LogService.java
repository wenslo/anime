package org.wen.service;

import org.wen.entity.Log;

import java.util.Date;
import java.util.List;

public interface LogService {
    List<Log> queryAll();
    Log queryOne(int id);
    List<Log> queryByCondition(String username, String common, Date createDate);
    int saveLog(Log log);
    int updateLog(int id);
}
