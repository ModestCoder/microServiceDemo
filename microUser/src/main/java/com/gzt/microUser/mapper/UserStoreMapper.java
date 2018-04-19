package com.gzt.microUser.mapper;

import com.microCommon.Entity.user.UserStore;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * User-Store
 * 
 * @author chenzhenwei
 * 
 * Wed Dec 09 16:06:23 CST 2015
 */
public interface UserStoreMapper{

    /**
     * 根据userId删除userStore关联信息
     * @param userId
     * @return
     */
    public boolean deleteByUserId(final Integer userId);

    /**
     * 根据storeId删除userStore关联信息
     * @param storeId
     * @return
     */
    public boolean deleteByStoreId(final Integer storeId);

    @Results({
            @Result(id=true,property="id",column="id"),
            @Result(property="adminUserId",column="admin_user_id"),
            @Result(property="storeId",column="store_id")
    })
    @Select("select * from sys_user_store where admin_user_id=#{userId}")
    List<UserStore> findByUserId(Integer userId);

}
