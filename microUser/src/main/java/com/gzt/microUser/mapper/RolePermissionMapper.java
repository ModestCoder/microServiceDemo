package com.gzt.microUser.mapper;

/**
 * Role-Permissions
 * 
 * @author chenzhenwei
 * 
 * Wed Dec 09 16:02:56 CST 2015
 */
public interface RolePermissionMapper {

    /**
     * 根据roleId删除role permission管理信息
     * @param roleId
     * @return
     */
    public boolean deleteByRoleId(final Integer roleId);

    /**
     * 根据permissionId删除role permission管理信息
     * @param permissionId
     * @return
     */
    public boolean deleteByPermissionId(final Integer permissionId);
	
}
