package org.wen.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wen.dao.LogDao;
import org.wen.dao.RoleDao;
import org.wen.dto.Result;
import org.wen.entity.DataGrid;
import org.wen.entity.Log;
import org.wen.entity.User;
import org.wen.section.SystemServiceLog;
import org.wen.service.LogService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Transactional
public class LogServiceImpl implements LogService{
    @Resource
    private LogDao logDao;
    @Resource
    private RoleDao roleDao;
//    @SystemServiceLog(description = "日志列表")
    public DataGrid datagrid(String common,int page,int rows,User user) {
        DataGrid dg = new DataGrid();
        Map<String, Object> params = Maps.newHashMap();
        params.put("common",common);
        params.put("page",rows*(page-1));
        params.put("rows",rows);
        params.put("userId",user.getId());
        List<Log> l = logDao.find(params);
        dg.setTotal(logDao.count(params));
        dg.setRows(l);
        return dg;
    }
//    @SystemServiceLog(description = "查看下属的日志列表")
    public DataGrid datagrid(String common,int page,int rows,User user,String otherLog) {
        //先查出该用户属于那种角色，角色下的所有用户
        Integer userId = user.getId();
        List<Integer> userIds = roleDao.findLessUsers(userId);
        DataGrid dg = new DataGrid();
        Map<String, Object> params = Maps.newHashMap();
        params.put("common",common);
        params.put("page",rows*(page-1));
        params.put("rows",rows);
        params.put("userId",userIds);
        List<Log> l = logDao.findOhter(params);
        dg.setTotal(logDao.countOther(params));
        dg.setRows(l);
        return dg;
    }
//    @SystemServiceLog(description = "添加日志")
    public Result addLog(String name, String projectName, String common, String createDate,User user) throws Exception{
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Result result = new Result();
        Date create = sdf.parse(createDate);
        Log log = new Log(name,common,projectName,create);
        log.setUserId(user.getId());
        int flag = logDao.save(log);
        if(flag > 0){
            log.setId(flag);
            log.setState("4");
            result.setResult(1);
            result.setMessage("添加日志成功！");
            result.setData(log);
            return result;
        }
//        AtomicInteger index = new AtomicInteger(0);
//        index.getAndIncrement();
        return null;
    }
//    @SystemServiceLog(description = "删除日志")
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
//    @SystemServiceLog(description = "查询Excel需要的数据")
    public List<Map<String, Object>> queryMap(String ids) {
        if("quanbu".equals(ids)){
            List<Log> logs = logDao.findAll();
            return dataWrite(logs);
        }
        String userId = ids.substring(0,ids.lastIndexOf(","));
        String[] id = userId.split(",");
        ArrayList<Long> list = Lists.newArrayList();
        for(String number :id){
            list.add(Long.parseLong(number));
        }
        List<Log> logs = logDao.findByIds(list);
        return dataWrite(logs);
    }
//    @SystemServiceLog(description = "根据ID查询日志")
    public Log getLog(String id) {
        return logDao.findById(Integer.parseInt(id));
    }
//    @SystemServiceLog(description = "日志数据封装")
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
