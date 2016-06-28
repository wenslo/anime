package org.wen.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wen.entity.Tmenu;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wen on 2016/6/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class MenuDaoTest {
    @Resource
    private MenuDao menuDao;
    @Test
    public void testFindAll() throws Exception {
        List<Tmenu> list =  menuDao.findAll();
        System.out.println(list.toString());
    }

    @Test
    public void testFindByPid() throws Exception {
        List<Tmenu> list =  menuDao.findByPid("0");
        System.out.println(list.toString());
    }
}