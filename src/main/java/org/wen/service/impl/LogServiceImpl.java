package org.wen.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wen.dao.LogDao;
import org.wen.dto.Result;
import org.wen.entity.DataGrid;
import org.wen.entity.Log;
import org.wen.entity.User;
import org.wen.service.LogService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class LogServiceImpl implements LogService{
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource
    private LogDao logDao;
    public DataGrid datagrid(String common,int page,int rows) {
        DataGrid dg = new DataGrid();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("common",common);
        params.put("page",page-1);
        params.put("rows",rows);
        List<Log> l = logDao.find(params);
        dg.setTotal(logDao.count(params));
        dg.setRows(l);
        return dg;
    }

    public Result addLog(String name, String projectName, String common, String createDate) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Result result = new Result();
        Date create = sdf.parse(createDate);
        Log log = new Log(name,common,projectName,create);
        int flag = logDao.save(log);
        if(flag > 0){
            log.setId(flag);
            log.setState("4");
            result.setResult(1);
            result.setMessage("添加日志成功！");
            result.setData(log);
            return result;
        }
        return null;
    }

    public Result deleteLog(String ids) {
        Result result = new Result();
        String[] id = ids.split(",");
        ArrayList<Long> list = new ArrayList<Long>();
        for(String number :id){
            list.add(Long.parseLong(number));
        }
        int count = logDao.deleteById(list);
        result.setMessage("删除成功！一共删除了"+count+"条数据");
        result.setResult(1);
        return result;
    }
}
