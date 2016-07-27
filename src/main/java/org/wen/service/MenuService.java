package org.wen.service;

import org.wen.entity.Menu;

import java.util.List;

/**
 * 数列表
 */
public interface MenuService {
    List<Menu> getTreeNode(String id);
    List<Menu> getAllTreeNode();
}
