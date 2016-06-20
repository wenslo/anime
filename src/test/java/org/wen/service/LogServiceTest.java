package org.wen.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.wen.entity.Log;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by wen on 2016/6/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class LogServiceTest {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LogService logService;
    @Test
    public void testQueryAll() throws Exception {
        List<Log> list = logService.queryAll();
        for(Log log :list){
            logger.info("LogList={}",log.getProjectName());
        }
    }

    @Test
    public void testQueryOne() throws Exception {

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
}