package org.wen.entity;

import java.util.Date;

/**
 * ��־ʵ����
 * 2016��6��6��21:35:37
 * �º���
 */
public class Log {
    /**
     * ID
     */
    private Integer id;
    /**
     * ����
     */
    private String name;
    /**
     * ��־����
     */
    private String common;
    /**
     * ��Ŀ����
     */
    private String projectName;
    /**
     * ����ʱ��
     */
    private Date createDate;
    /**
     * �޸�ʱ��
     */
    private Date updateDate;
    /**
     * ״̬
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
