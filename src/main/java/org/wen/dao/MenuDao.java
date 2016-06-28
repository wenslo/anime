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
     * @return
     */
    public List<Tmenu> findFirst();

    /**
     * 异步加载当前ID下的节点
     * @param id
     * @return
     */
    public List<Tmenu> findByPid(String id);
    /**
     * 根据ID找到节点
     * @param id
     * @return
     */
    public Tmenu findById(String id);
    /**
     * 查询所有菜单
     * @return
     */
    public List<Tmenu> findAll();
}
