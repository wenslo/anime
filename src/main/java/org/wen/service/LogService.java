package org.wen.service;

import org.wen.dto.Result;
import org.wen.entity.DataGrid;
import org.wen.entity.Log;

import java.util.Date;
import java.util.List;

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
}
