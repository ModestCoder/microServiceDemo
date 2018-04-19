package com.gzt.microCommon.service.user;

import com.gzt.microCommon.Entity.user.Role;

import javax.jws.WebService;


/**
 * Role
 * 
 * @author chenzhenwei
 * 
 * Wed Dec 09 16:00:52 CST 2015
 */
@WebService
public interface RoleService {
//    public boolean savePermission(Integer id, Integer[] permissionIds);
    /**
     * 使用pager查询,并包含query参数
     *
     * @param pager
     * @return
     */
//    Result<Role> findByPager(Pager pager);

    /**
     * 根据storeId获取roleList列表
     * @param storeId
     * @return
     */
//    List<Role> findRoleListByStoreId(List<Integer> storeId);

    Role find(Integer id);
}

