package org.wen.service;

import org.wen.entity.DataGrid;
import org.wen.entity.Menu;

import java.util.List;

/**
 * 数列表
 */
public interface MenuService {
    List<Menu> getTreeNode(String id);
    List<Menu> getAllTreeNode();
    /**
     * 数据列表
     * @param page 页数
     * @param rows 行数
     * @return 数据列表
     */
    DataGrid datagrid(int page, int rows);
}
