package org.wen.dto;

/**
 * Created by wen on 2016/7/26.
 */
public class Table {
    public String tableName;
    private String common;

    @Override
    public String toString() {
        return "Table{" +
                "tableName='" + tableName + '\'' +
                ", common='" + common + '\'' +
                '}';
    }

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
