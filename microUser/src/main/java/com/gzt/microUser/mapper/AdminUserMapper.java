package com.gzt.microUser.mapper;

import com.microCommon.Entity.user.AdminUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * 功能描述：用户信息
 * 
 * @author csj
 */
public interface AdminUserMapper {

    @Select("SELECT * FROM sys_admin_user WHERE id = #{userId}")
    String findPasswordByUserId(Integer userId);

    @Results({
            @org.apache.ibatis.annotations.Result(id=true,property="isAdmin",column="is_admin"),
            @org.apache.ibatis.annotations.Result(property="lastLoginTime",column="last_login_time"),
    })
    @Select("select * from sys_admin_user WHERE name = #{name} and password = #{password}")
    AdminUser find(@Param("name") String name, @Param("password") String password);

    @Update("update sys_admin_user set last_login_time = #{lastLoginTime},error_times = #{errorTimes}," +
            "password = #{password},default_store=#{defaultStore}")
    boolean saveOrUpdate(AdminUser user);
}
