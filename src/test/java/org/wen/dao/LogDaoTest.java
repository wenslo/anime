package org.wen.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wen.entity.Log;

import javax.annotation.Resource;

import java.util.List;

import static org.junit.Assert.*;

/**
 * 配置spring和junit整合,junit启动时加载springIOC容器
 * LogDao的测试类
 * 测试DAO方法是否可行
 * 2016年6月13日23:29:10
 */
@RunWith(SpringJUnit4ClassRunner.class)
//告诉junit spring配置文件
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class LogDaoTest {

    //注入Dao实现类
    @Resource
    private LogDao logDao;

    @Test
    public void testQueryAll() throws Exception {
        List<Log> list = logDao.queryAll();
        System.out.println("------------------------------------------------------->");
        System.out.println(list);
    }

    @Test
    public void testQueryById() throws Exception {

    }

    @Test
    public void testQueryByCondition() throws Exception {

    }

    @Test
    public void testSaveLog() throws Exception {

    }

    @Test
    public void testUpdateLog() throws Exception {

    }

    @Test
    public void testDeleteLog() throws Exception {

    }
}