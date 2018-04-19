package com.gzt.microUser.mapper;

import com.microCommon.Entity.user.UserRole;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * User-Role
 * 
 * @author chenzhenwei
 * 
 * Wed Dec 09 16:05:27 CST 2015
 */
public interface UserRoleMapper{

    /**
     * 级联查询
     * @param subSelectParam
     * @return
     */
//    List<UserRole> findByAdminUserId(SubSelectParam subSelectParam);

    /**
     * 级联查询
     * @param pager
     * @return
     */
//    List<AdminUser> findUserList(final Pager pager, final Integer roleId);

    /**
     * 根据adminUserId删除用户用户组关联列表
     * @param adminUserId
     * @return
     */
    boolean deleteByUserId(final Integer adminUserId);

    /**
     * 根据roleId删除用户用户组关联列表
     * @param roleId
     * @return
     */
    boolean deleteByRoleId(final Integer roleId);

    @Results({
            @Result(id=true,property="id",column="id"),
            @Result(property="adminUserId",column="admin_user_id"),
            @Result(property="roleId",column="role_id")
    })
    @Select("select * from sys_user_role where admin_user_id=#{userId}")
    List<UserRole> findByUserId(Integer userId);
}
