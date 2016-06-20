package org.wen.dao;

import org.wen.entity.Log;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * ������־���DAO��
 * 2016��6��6��21:38:40
 * �º���
 */
public interface LogDao {
    /**
     * ��Ҫ��һ���ܹ����ҳ����е���־��¼�ķ���
     * @return
     */
    List<Log> queryAll();
    /**
     * ��Ҫ�в鿴����һ���ķ������Ա����ڲ鿴����
     * @return
     */
    Log queryById(int id);
    /**
     * ��Ҫ��һ����ѯ����
     * @return
     */
    List<Log> queryByCondition(Map map);
    /**
     * ��Ҫ�и����ӵķ���
     * @param log
     * @return
     */
    void saveLog(Log log);
    /**
     * ��Hһ���޸ĵķ���
     * @param id
     * @param log
     * @return
     */
    int updateLog(int id, Log log);
    /**
     * ��Ҫ��һ��ɾ����־�ķ���
     * @param id
     * @return
     */
    int deleteLog(int id);
}
