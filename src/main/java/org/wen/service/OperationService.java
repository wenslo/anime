package org.wen.service;

import org.wen.entity.DataGrid;

import java.util.List;
import java.util.Map;

/**
 * 系统操作日志的Srivice接口，记录在日志系统中的操作
 * 2016年7月28日17:45:53
 * @author 温海林
 */
public interface OperationService {
    /**
     * 操作日志的分页先暂时让其能够查看所有用户的日志
     * 2016年7月28日17:47:16
     * 温海林
     * @param page 页数
     * @param rows 行数
     * @return 返回操作信息
     */
    DataGrid datagrid(int page,int rows);

    /**
     * 查询Excel导出需要的数据
     * 2016年8月1日23:18:25
     * 温海林
     * @param ids 数据
     * @return 封装后的数据
     */
    List<Map<String,Object>> queryMap();
}
