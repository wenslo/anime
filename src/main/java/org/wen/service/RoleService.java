package org.wen.service;

import org.wen.entity.DataGrid;

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
}
