package org.wen.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wen.dao.LogDao;
import org.wen.entity.Log;
import org.wen.service.LogService;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ��־Service��ʵ����
 * 2016��6��15��00:12:38
 * �º���
 */
@Service
@Transactional
public class LogServiceImpl implements LogService{
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Resource
    private LogDao logDao;
    public List<Log> queryAll() {
        List<Log> list = logDao.queryAll();
        return list;
    }

    public Log queryOne(int id) {
        Log log = logDao.queryById(id);
        return log;
    }

    public List<Log> queryByCondition(String username, String common, Date createDate) {
        Map map = new HashMap();
        map.put("username",username);
        map.put("common",common);
        map.put("createDate",createDate);
        List<Log> list = logDao.queryByCondition(map);
        return null;
    }
    @Transactional(readOnly = false)
    public int saveLog(Log log) {
        logDao.saveLog(log);
        return 0;
    }
    @Transactional(readOnly = false)
    public int updateLog(int id) {
        Log log = new Log();
        return logDao.updateLog(id,log);
    }
}
