package org.wen.service;

import org.wen.dto.Result;
import org.wen.entity.DataGrid;
import org.wen.entity.Role;

import java.util.List;
import java.util.Map;

/**
 * 角色Service
 * 2016年7月19日00:08:10
 * 温海林
 */
public interface RoleService {
    /**
     * 角色列表的分页
     * @param common
     * @param page
     * @param rows
     * @return
     */
    public DataGrid datagrid(String common, int page, int rows);

    /**
     * 添加角色
     * @param roleNumber
     * @param roleName1
     * @return
     */
    public Result addRole(String roleNumber, String roleName1);

    /**
     * 删除角色
     * @param ids
     * @return
     */
    public Result deleteRole(String ids);

    /**
     * 导出Excel需要的数据，并进行封装
     * @param ids
     * @return
     */
    public List<Map<String,Object>> queryMap(String ids);

    /**
     * 查询所有的角色信息
     * @return
     */
    public List<Role> getRole();

    /**
     * 添加中间表数据，添加用户的角色
     * @return
     */
    public Result addMis(String userId,String roleId);
}
