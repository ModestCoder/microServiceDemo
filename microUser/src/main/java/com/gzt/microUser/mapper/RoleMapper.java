package com.gzt.microUser.mapper;

import com.gzt.springBootDemo.model.role.Role;
import org.apache.ibatis.annotations.Select;

/**
 * Role
 * 
 * @author chenzhenwei
 * 
 * Wed Dec 09 16:00:52 CST 2015
 */
public interface RoleMapper {
    @Select("select * from sys_role where id=#{id}")
    Role find(Integer id);
}
