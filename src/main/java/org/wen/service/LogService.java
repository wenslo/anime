package org.wen.service;

import org.wen.dto.Result;
import org.wen.entity.DataGrid;
import org.wen.entity.Log;
import org.wen.entity.User;

import java.util.List;
import java.util.Map;

public interface LogService {
    /**
     * 数据列表
     * @param common 项目内容
     * @param page 页数
     * @param rows 行数
     * @return 数据列表
     */
    DataGrid datagrid(String common,int page,int rows,User user);

    /**
     * 查看下属日志
     * @param common 查询内容
     * @param page 页数
     * @param rows 行数
     * @param user 用户
     * @param otherLog 下属
     * @return 数据列表
     */
    DataGrid datagrid(String common,int page,int rows,User user,String otherLog);
    /**
     * 添加日志
     * @param naname 名称
     * @param projectName 所属项目
     * @param common 内容
     * @param createDate 创建时间
     * @return 添加结果
     */
    Result addLog(String naname, String projectName, String common, String createDate,User user) throws Exception;

    /**
     * 删除日志
     * @param ids 删除的日志的ID
     * @return 删除条数
     */
    Result deleteLog(String ids);

    /**
     * 查询导出日志所需要的数据，对其进行数据封装
     * 2016年7月11日21:28:25
     * @param ids 日志ID
     * @return 日志集合
     */
    List<Map<String,Object>> queryMap(String ids);

    /**
     * 根据传入的ID
     * @return 日志
     */
    Log getLog(String id);
}
