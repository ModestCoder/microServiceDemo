package com.gzt.microCommon.service.user;

import com.gzt.microCommon.Entity.user.Permission;

import java.util.List;

/**
 * Created by gaozhiting on 2018/3/13.
 */
public interface PermissionService {
    /**
     * 改变启用停用状态
     *
     * @param id
     * @return
     */
//    boolean changeStatus(Integer id);

    /**
     * 排序
     *
     * @param id
     * @param upper 上升还是下降
     * @return
     */
//    boolean sort(Integer id, boolean upper);

    /**
     * 按父id查询
     *
     * @param parentId
     * @return
     */
//    List<Permissions> findByParentId(Integer parentId);

    /**
     * 按组id查询 , 组id字符串,1,2,3,4,查询非禁用的且为菜单的权限
     *
     * @param groupIds
     * @return
     */
//    List<Permissions> findMenuByGroupIds(String groupIds);

    /**
     * 检查权限路径是否存在组id中
     *
     * @param path
     * @param groupIds
     * @return
     */
//    boolean checkPermission(String path, String groupIds);

    /**
     * 根据role查询所有权限
     * @param roleId
     * @return
     */
    List<Permission> findPermissionByRoleId(Integer roleId);

//    List<Permissions> findAllMenu();

    List<Permission> findAll();
}
