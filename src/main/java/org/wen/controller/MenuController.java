package org.wen.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.wen.entity.Menu;
import org.wen.section.SystemControllerLog;
import org.wen.service.MenuService;

import java.util.List;

/**
 * 树列表的Action
 * 2016年6月23日20:59:45
 * 温海林
 */
@Controller
@RequestMapping("/menu")
public class MenuController {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private MenuService menuService;
    /**
     * 异步获取树节点
     */
    @RequestMapping("/treeNode")
    @ResponseBody
    @SystemControllerLog(description = "树列表节点")
    public List getTreeNode(String id) {
        List<Menu> list = menuService.getTreeNode(id);
        log.debug("数据为{}",list.toString());
        return list;
    }
    @RequestMapping("/allTree")
    @ResponseBody
    @SystemControllerLog(description = "所有树列表")
    public List getAllTreeNode() {
        return menuService.getAllTreeNode();
    }
}
