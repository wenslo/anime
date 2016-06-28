package org.wen.service;

import org.wen.entity.Menu;

import java.util.List;

/**
 * 数列表
 */
public interface MenuService {
    public List<Menu> getTreeNode(String id);
    public List<Menu> getAllTreeNode();
}
