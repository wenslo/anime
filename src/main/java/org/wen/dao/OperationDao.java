package org.wen.dao;

import org.wen.entity.DataGrid;
import org.wen.entity.Operation;

import java.util.List;
import java.util.Map;

/**
 * 操作日志类的DAO层
 * 2016年7月29日00:23:06
 * @author 温海林
 */
public interface OperationDao {
    /**
     * 查找对应的操作日志记录
     * 2016年7月29日00:23:42
     * 温海林
     * @param map 分页参数
     * @return 数据列表
     */
    List<Operation> find(Map map);

    /**
     * 查询操作日志总数
     * 2016年7月29日00:24:06
     * 温海林
     * @return 日志总数
     */
    Long count();

    /**
     * 添加日志
     * 2016年7月29日00:38:13
     * 温海林
     * @param o 日志信息
     */
    void save(Operation o);
}
