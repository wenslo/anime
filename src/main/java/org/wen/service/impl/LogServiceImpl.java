package org.wen.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
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
        Map<String, Object> params = Maps.newHashMap();
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
        ArrayList<Long> list = Lists.newArrayList();
        for(String number :id){
            list.add(Long.parseLong(number));
        }
        int count = logDao.deleteById(list);
        result.setMessage("删除成功！一共删除了"+count+"条数据");
        result.setResult(1);
        return result;
    }

    public List<Map<String, Object>> queryMap(String ids) {
        if("quanbu".equals(ids)){
            List<Log> logs = logDao.findAll();
            List<Map<String, Object>> original = dataWrite(logs);
            return original;
        }
        String userId = ids.substring(0,ids.lastIndexOf(","));
        String[] id = ids.split(",");
        ArrayList<Long> list = Lists.newArrayList();
        for(String number :id){
            list.add(Long.parseLong(number));
        }
        List<Log> logs = logDao.findByIds(list);
        List<Map<String, Object>> original = dataWrite(logs);
        return original;
    }

    public Log getLog(String id) {
        return logDao.findById(Integer.parseInt(id));
    }

    public List<Map<String,Object>> dataWrite(List<Log> logs) {
        List<Map<String,Object>> original = new ArrayList<Map<String, Object>>();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Log log :logs){
            Map<String,Object > map = Maps.newHashMap();
            map.put("name", log.getName());
            map.put("common", log.getCommon());
            map.put("projectName",log.getProjectName());
            map.put("createDate",sdf.format(log.getCreateDate()));
            map.put("updateDate",log.getUpdateDate()!=null?sdf.format(log.getUpdateDate()):"");
            switch (Integer.parseInt(log.getState())){
                case 0:
                    map.put("state","好");
                    break;
                case 1:
                    map.put("state","完成");
                    break;
                case 2:
                    map.put("state","差");
                    break;
                case 3:
                    map.put("state","未完成");
                    break;
                case 4:
                    map.put("state","未审核");
                    break;
            }
            original.add(map);
        }
        return original;
    }
}
