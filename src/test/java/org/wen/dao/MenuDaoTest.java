package org.wen.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wen.dto.Table;
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
    private UserDao userDao;
    @Test
    public void testFindTable() throws Exception {
        List<Table> tables = (List<Table>) userDao.findTable();
        for (Table table :tables){
            System.out.println(table.toString()+"/t");
        }
    }
}