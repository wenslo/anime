package org.wen.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.wen.dao.MenuDao;
import org.wen.entity.DataGrid;
import org.wen.entity.Menu;
import org.wen.entity.Tmenu;
import org.wen.section.SystemServiceLog;
import org.wen.service.MenuService;

import javax.annotation.Resource;
import java.util.*;

/**
 * 树列表service层的实现
 * 2016年7月27日16:53:35
 * @author 温海林
 */
@Service
@Transactional
public class MenuServiceImpl implements MenuService{
    @Resource
    private MenuDao menuDao;
//    @SystemServiceLog(description = "查询所有树节点")
    public List<Menu> getTreeNode(String id) {
        List<Menu> n1 = new ArrayList<Menu>();
        List<Tmenu> l;
        if(id == null || id.equals("")){
            l = menuDao.findFirst();
        }else{
            l = menuDao.findByPid(id);
        }
        if(l!=null && l.size()>0){
            for(Tmenu t :l){
                Menu m = new Menu();
                m.setText(t.getText());
                m.setPid(t.getPid());
                m.setId(t.getId());
                Map<String,Object> map = new HashMap<String, Object>();
                map.put("url",t.getUrl());
                m.setAttributes(map);
                String pid = t.getId();
                List<Tmenu> list = menuDao.findByPid(pid==null?"0":pid);
                if(list != null && !list.isEmpty()){
                    m.setState("closed");
                }else{
                    m.setState("open");
                }
                n1.add(m);
            }
        }
        return n1;
    }

    public List<Menu> getAllTreeNode() {
        List<Menu> n1 = new ArrayList<Menu>();
        List<Tmenu> l = menuDao.findAll();
        if (l != null && l.size() > 0) {
            for (Tmenu t : l) {
                Menu m = new Menu();
                m.setPid(t.getPid());
                m.setText(t.getText());
                Map<String, Object> attributes = new HashMap<String, Object>();
                attributes.put("url", t.getUrl());
                m.setAttributes(attributes);
                Tmenu tm = menuDao.findById(t.getPid());// 获得当前节点的父节点对象
                if (tm != null) {
                    m.setPid(tm.getId());
                }
                n1.add(m);
            }
        }
        return n1;
    }

    public DataGrid datagrid(int page, int rows) {
        DataGrid dg = new DataGrid();
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("page",rows*(page-1));
        params.put("rows",rows);
        List<Tmenu> l = menuDao.find(params);
        dg.setTotal(menuDao.count(params));
        dg.setRows(l);
        return dg;
    }
}
