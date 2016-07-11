package org.wen.entity;

/**
 * Created by wen on 2016/7/9.
 */
public class Project {
    private Integer id;
    private String projectName;

    @Override
    public String toString() {
        return "Project{" +
                "id=" + id +
                ", projectName='" + projectName + '\'' +
                '}';
    }

    public Project() {
    }

    public Project(Integer id, String projectName) {

        this.id = id;
        this.projectName = projectName;
    }

    public Integer getId() {

        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }
}
