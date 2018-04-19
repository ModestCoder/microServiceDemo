package com.gzt.microCommon.service.user;

import com.gzt.microCommon.Entity.user.UserRole;

import javax.jws.WebService;
import java.util.List;

/**
 * User-Role
 * 
 * @author chenzhenwei
 * 
 * Wed Dec 09 16:05:27 CST 2015
 */
@WebService
public interface UserRoleService {

    /**
     * 根据userId查询UserRole列表
     * @param userId
     * @return
     */
    List<UserRole> findByUserId(Integer userId);

    /**
     * 根据roleId查询UserRole列表
     * @param roleId
     * @return
     */
//    public Result<UserRole> findRoleListByPager(Pager pager, Integer roleId);

    /**
     * 用户组列表
     * @param pager
     * @return
     */
//    List<UserRole> findByPager(Pager pager);
}
