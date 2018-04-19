package com.gzt.microUser.mapper;

import com.microCommon.Entity.user.Store;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Store
 *
 * @author chenzhenwei
 *
 * Wed Dec 09 16:04:08 CST 2015
 */
public interface StoreMapper {
    @Select("select * from sys_store where id = #{id}")
    Store find(@Param("id") Integer id);

    @Results({
            @Result(id=true,property="id",column="id"),
            @Result(property="name",column="name"),
            @Result(property="parentId",column="parent_id"),
            @Result(id=true,property="code",column="code"),
            @Result(property="lev",column="lev"),
            @Result(property="sortOrder",column="sort_order")
    })
    @Select("select * from sys_store where code like CONCAT(#{code},'%') order by lev")
    List<Store> likeByCode(@Param("code") String code);

    @Results({
            @Result(id=true,property="id",column="id"),
            @Result(property="name",column="name"),
            @Result(property="parentId",column="parent_id"),
            @Result(id=true,property="code",column="code"),
            @Result(property="lev",column="lev"),
            @Result(property="sortOrder",column="sort_order")
    })
    @Select("select * from sys_store order by id")
    List<Store> findAll();
}