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
 * ����spring��junit����,junit����ʱ����springIOC����
 * LogDao�Ĳ�����
 * ����DAO�����Ƿ����
 * 2016��6��13��23:29:10
 */
@RunWith(SpringJUnit4ClassRunner.class)
//����junit spring�����ļ�
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class LogDaoTest {


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