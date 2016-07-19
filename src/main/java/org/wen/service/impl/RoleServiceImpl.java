package org.wen.service.impl;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wen.dao.RoleDao;
import org.wen.dto.Result;
import org.wen.entity.DataGrid;
import org.wen.entity.Role;
import org.wen.service.RoleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by wen on 2016/7/19.
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public RoleDao roleDao;
    public DataGrid datagrid(String common, int page, int rows) {
        DataGrid dg = new DataGrid();
        Map<String, Object> params = Maps.newHashMap();
        params.put("common",common);
        params.put("page",rows*(page-1));
        params.put("rows",rows);
        List<Role> l = roleDao.find(params);
        dg.setTotal(roleDao.count(params));
        dg.setRows(l);
        return dg;
    }

    public Result addRole(String roleNumber, String roleName) {
        Result result = new Result();
        Role role = new Role(Integer.parseInt(roleNumber),roleName);
        int flag = roleDao.save(role);
        if(flag > 0){
            log.info("保存角色成功");
            role.setId(flag);
            result.setResult(1);
            result.setMessage("添加角色成功！");
            result.setData(role);
            return result;
        }
        return null;
    }

    public Result deleteRole(String ids) {
        Result result = new Result();
        String[] id = ids.split(",");
        ArrayList<Long> list = Lists.newArrayList();
        for(String number :id){
            list.add(Long.parseLong(number));
        }
        int count = roleDao.deleteById(list);
        result.setMessage("删除成功！一共删除了"+count+"条数据");
        result.setResult(1);
        return result;
    }
}
