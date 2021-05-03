package vn.myclass.core.dto;


import java.io.Serializable;

public class RoleDTO implements Serializable {
    private Integer roleID;
    private String name;

    public Integer getRoleID() {
        return roleID;
    }

    public void setRoleID(Integer roleID) {
        this.roleID = roleID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
