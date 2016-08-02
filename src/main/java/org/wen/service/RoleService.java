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
     * @param common 内容
     * @param page 页数
     * @param rows 行数
     * @return 数据列表
     */
    DataGrid datagrid(String common, int page, int rows);

    /**
     * 添加角色
     * @param roleNumber 角色数字
     * @param roleName1 角色名称
     * @return 添加结果
     */
    Result addRole(String roleNumber, String roleName1);

    /**
     * 删除角色
     * @param ids 角色集合
     * @return 删除结果
     */
    Result deleteRole(String ids);

    /**
     * 导出Excel需要的数据，并进行封装
     * @param ids 角色ID集合
     * @return 数据列表
     */
    List<Map<String,Object>> queryMap(String ids);

    /**
     * 查询所有的角色信息
     * @return 角色LIST
     */
    List<Role> getRole();

    /**
     * 添加中间表数据，添加用户的角色
     * @return 添加结果
     */
    Result addMis(String userId,String roleId);

    /**
     * 验证角色数字。判断其是否合法
     * 2016年7月31日22:40:12
     * 温海林
     * @param roleNumber 角色数字
     * @return 返回判断结果
     */
    Result checkRole(Long roleNumber);
}
