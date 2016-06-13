package org.wen.entity;

import java.util.Date;

/**
 * 日志实体类
 * 2016年6月6日21:35:37
 * 温海林
 */
public class Log {
    /**
     * ID
     */
    private Integer id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 日志内容
     */
    private String common;
    /**
     * 项目名称
     */
    private String projectName;
    /**
     * 创建时间
     */
    private Date createDate;
    /**
     * 修改时间
     */
    private Date updateDate;
    /**
     * 状态
     */
    private String state;

    @Override
    public String toString() {
        return "Log{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", common='" + common + '\'' +
                ", projectName='" + projectName + '\'' +
                ", createDate=" + createDate +
                ", updateDate=" + updateDate +
                ", state='" + state + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCommon() {
        return common;
    }

    public void setCommon(String common) {
        this.common = common;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
