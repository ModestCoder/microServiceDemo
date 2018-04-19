package com.gzt.microCommon.Entity.user;

/**
 * Role-Permissions
 *
 * @author chenzhenwei
 *         <p>
 *         Wed Dec 09 16:02:56 CST 2015
 */
public class RolePermission {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public RolePermission() {
    }

    private Integer id;

    private Integer roleId;

    private Integer permissionId;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    public Integer getPermissionId() {
        return this.permissionId;
    }


}
