package org.wen.dto;

/**
 * 数据库的表名和注释的实体类
 * 2016年7月27日16:37:59
 * @author 温海林
 */
public class Table {
    private String tableName;
    private String common;

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }
}
