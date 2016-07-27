package org.wen.service.impl;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wen.dao.RoleDao;
import org.wen.dao.UserDao;
import org.wen.dto.Result;
import org.wen.entity.DataGrid;
import org.wen.entity.Role;
import org.wen.section.SystemServiceLog;
import org.wen.service.RoleService;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 角色的实现
 */
@Service
@Transactional
public class RoleServiceImpl implements RoleService {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    public RoleDao roleDao;
    @Autowired
    public UserDao userDao;
    @SystemServiceLog(description = "角色列表")
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
    @SystemServiceLog(description = "角色添加")
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
    @SystemServiceLog(description = "角色删除")
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
    @SystemServiceLog(description = "Excel所需数据查询")
    public List<Map<String, Object>> queryMap(String ids) {
        if("quanbu".equals(ids)){
            List<Role> users = roleDao.findAll();
            return dataWrite(users);
        }
        String roleId = ids.substring(0,ids.lastIndexOf(","));
        String[] id = roleId.split(",");
        ArrayList<Long> list = Lists.newArrayList();
        for(String number :id){
            list.add(Long.parseLong(number));
        }
        List<Role> users = roleDao.findByIds(list);
        return dataWrite(users);
    }
    @SystemServiceLog(description = "查询所有角色")
    public List<Role> getRole() {
        return roleDao.findAll();
    }
    @SystemServiceLog(description = "设置角色，中间表插入数据")
    public Result addMis(String userId,String roleId) {
        Map<String,Integer> map = Maps.newHashMap();
        map.put("userId", Integer.parseInt(Preconditions.checkNotNull(userId)));
        map.put("roleId",Integer.parseInt(Preconditions.checkNotNull(roleId)));
        int flag = roleDao.addMis(map);
        if(flag > 0){
            log.info("中间表数据插入成功！用户ID为{},角色ID为{}",userId,roleId);
        }
        int tag = userDao.insertRole(map);
        if(flag > 0 && tag > 0){
            Result result = new Result();
            result.setResult(1);
            result.setMessage("设置角色成功！");
            return result;
        }
        return null;
    }

    /**
     * 用于Excel导出的数据封装
     * @param roles 查询到的角色
     * @return 封装后的角色MAP
     */
    @SystemServiceLog(description = "数据封装")
    public List<Map<String, Object>> dataWrite(List<Role> roles) {
        List<Map<String,Object>> original = Lists.newArrayList();
        for(Role r :roles){
            Map<String,Object > map = Maps.newHashMap();
            map.put("roleNumber",r.getRoleNumber());
            map.put("name",r.getRoleName());
            original.add(map);
        }
        return original;
    }
}
