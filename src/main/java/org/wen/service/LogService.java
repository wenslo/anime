package org.wen.service;

import org.wen.entity.Log;

import java.util.Date;
import java.util.List;

/**
 * ��־service�ӿڶ���
 * 2016��6��14��22:02:09
 * �º���
 */
public interface LogService {
    /**
     * ��ѯ��������־
     * ������Ϸ�ҳ
     * @return
     */
    List<Log> queryAll();
    /**
     * �鿴���飬����ID���ҳ���Ӧ�������Ա��ڹۿ�
     * @return
     */
    Log queryOne();
    /**
     * ������־�ķ���
     * @param username
     * @param common
     * @param createDate
     * @return
     */
    List<Log> queryByCondition(String username, String common, Date createDate);
    /**
     * ��Ҫ��һ��������־�ķ���
     * @param log
     */
    int saveLog(Log log);
    /**
     * �޸���־
     * @param log  ��Ҫ�޸ĵ�ʵ����
     * @return
     */
    int updateLog(Log log);
}
