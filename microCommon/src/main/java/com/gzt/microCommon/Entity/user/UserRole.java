package com.gzt.microCommon.Entity.user;

/**
 * User-Role
 *
 * @author chenzhenwei
 *         <p>
 *         Wed Dec 09 16:05:27 CST 2015
 */
public class UserRole {
    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public UserRole() {
    }

    private Integer id;

    private Integer adminUserId;

    private Integer roleId;

    Role role;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(Integer adminUserId) {
        this.adminUserId = adminUserId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getRoleId() {
        return this.roleId;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
