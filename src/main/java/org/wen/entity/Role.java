package org.wen.entity;

/**
 * Created by wen on 2016/7/18.
 */
public class Role {
    private Integer id;
    private Integer roleNumber;
    private String roleName;
    public Role(){}
    public Role(Integer roleNumber, String roleName) {
        this.roleNumber = roleNumber;
        this.roleName = roleName;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRoleName() {
        return roleName;
    }

    public Integer getRoleNumber() {
        return roleNumber;
    }

    public void setRoleNumber(Integer roleNumber) {
        this.roleNumber = roleNumber;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
