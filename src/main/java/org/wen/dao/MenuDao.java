package org.wen.dao;

import org.wen.entity.Tmenu;

import java.util.List;

/**
 * 树列表的DAO层
 */
public interface MenuDao {
    /**
     * 查询所有根节点
     * 2016年6月24日00:16:06
     * 温海林
     * @return 返回根节点
     */
     List<Tmenu> findFirst();

    /**
     * 异步加载当前ID下的节点
     * @param id 父节点ID
     * @return 父节点下的树列表
     */
     List<Tmenu> findByPid(String id);
    /**
     * 根据ID找到节点
     * @param id 节点ID
     * @return 节点内容
     */
     Tmenu findById(String id);
    /**
     * 查询所有菜单
     * @return 所有节点
     */
     List<Tmenu> findAll();
}
