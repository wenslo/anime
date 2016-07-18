package org.wen.service.impl;

import com.google.common.collect.Maps;
import org.springframework.beans.factory.annotation.Autowired;
import org.wen.dao.RoleDao;
import org.wen.entity.DataGrid;
import org.wen.entity.Role;
import org.wen.service.RoleService;

import java.util.List;
import java.util.Map;

/**
 * Created by wen on 2016/7/19.
 */
public class RoleServiceImpl implements RoleService {
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
}
