package org.wen.service;

import org.wen.dto.Result;
import org.wen.entity.DataGrid;
import org.wen.entity.Log;

import java.util.Date;
import java.util.List;
import java.util.Map;

public interface LogService {
    /**
     * 数据列表
     * @param common 项目内容
     * @param page
     * @param rows
     * @return
     */
    public DataGrid datagrid(String common,int page,int rows);

    /**
     * 添加日志
     * @param naname
     * @param projectName
     * @param common
     * @param createDate
     * @return
     */
    public Result addLog(String naname, String projectName, String common, String createDate) throws Exception;

    /**
     * 删除日志
     * @param ids
     * @return
     */
    public Result deleteLog(String ids);

    /**
     * 查询导出日志所需要的数据，对其进行数据封装
     * 2016年7月11日21:28:25
     * @param ids
     * @return
     */
    public List<Map<String,Object>> queryMap(String ids);

    /**
     * 根据传入的ID
     * @return
     */
    public Log getLog(String id);
}
