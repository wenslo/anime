package org.wen.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 操作日志类
 * 2016年7月28日21:52:29
 * @author 温海林
 */
public class Operation {
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 主键

     */
    private Integer id;
    /**
     * 请求人
     */
    private String userName;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 方法描述
     */
    private String describeCommon;
    /**
     * 请求方法名称
     */
    private String methodName;

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateDate() {
        return createDate;
    }
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getDescribeCommon() {
        return describeCommon;
    }

    public void setDescribeCommon(String describeCommon) {
        this.describeCommon = describeCommon;
    }
}
