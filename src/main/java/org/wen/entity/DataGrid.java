package org.wen.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wen on 2016/6/27.
 */
public class DataGrid {

    private Long total = 0L;
    private List rows = new ArrayList();

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }

}
