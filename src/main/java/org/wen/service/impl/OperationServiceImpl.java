package org.wen.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wen.dao.OperationDao;
import org.wen.entity.DataGrid;
import org.wen.entity.Operation;
import org.wen.service.OperationService;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 操作日志实现类
 * 2016年7月28日17:49:07
 * @author 温海林
 */
@Service
@Transactional
public class OperationServiceImpl implements OperationService {
    @Resource
    private OperationDao operationDao;
    public DataGrid datagrid(int page, int rows) {
        DataGrid dg = new DataGrid();
        Map<String, Object> params = Maps.newHashMap();
        params.put("page",rows*(page-1));
        params.put("rows",rows);
        List<Operation> l = operationDao.find(params);
        dg.setTotal(operationDao.count());
        dg.setRows(l);
        return dg;
    }

    public List<Map<String, Object>> queryMap() {
        List<Operation> users = operationDao.findAll();
        return dataWrite(users);
    }

    /**
     * 数据封装
     * @param users 查询出来的所有的数据
     * @return 封装后的数据
     */
    private List<Map<String, Object>> dataWrite(List<Operation> users) {
        List<Map<String,Object>> original = Lists.newArrayList();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for(Operation o :users){
            Map<String,Object > map = Maps.newHashMap();
            map.put("createDate", sdf.format(o.getCreateDate()));
            map.put("describeCommon", o.getDescribeCommon());
            map.put("methodName",o.getMethodName());
            map.put("userName",o.getUserName());
            original.add(map);
        }
        return original;
    }
}
