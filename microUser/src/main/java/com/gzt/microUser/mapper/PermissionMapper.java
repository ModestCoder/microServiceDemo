package com.gzt.microUser.mapper;

//import com.coocaa.utils.Pager;
//import com.coocaa.utils.Result;
import com.gzt.springBootDemo.model.permission.Permission;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * Created by gaozhiting on 2018/3/13.
 */
public interface PermissionMapper {
    /**
     * 改变启用停用状态
     *
     * @param id
     * @return
     */
    boolean changeStatus(Integer id);

    /**
     * 排序
     *
     * @param id
     * @param upper 上升还是下降
     * @return
     */
    boolean sort(Integer id, boolean upper);

    /**
     * 按父id查询
     *
     * @param parentId
     * @return
     */
    List<Permission> findByParentId(Integer parentId);

    /**
     * 按组id查询 , 组id字符串,1,2,3,4,查询非禁用的且为菜单的权限
     *
     * @param groupIds
     * @return
     */
    List<Permission> findMenuByGroupIds(String groupIds);

    /**
     * 检查权限路径是否存在组id中
     *
     * @param path
     * @param groupIds
     * @return
     */
    boolean checkPermission(String path, String groupIds);

    /**
     * 根据role查询所有权限
     * @param roleId
     * @return
     */
    @Results({
            @org.apache.ibatis.annotations.Result(id=true,property="parentId",column="parent_id"),
            @org.apache.ibatis.annotations.Result(property="isButton",column="is_button"),
    })
    @Select("select * from sys_permission where id in (select permissionId from sys_role_permission where role_id = #{roleId}) order by code asc, lev asc ")
    List<Permission> findPermissionByRoleId(@Param("role_id") Integer roleId);

    List<Permission> findAllMenu();

    /**
     * 分页查询
     * findByPager
     * @param pager
     * @return
     */
//    Result<Permission> findByPager(Pager pager);

    @Results({
            @org.apache.ibatis.annotations.Result(id=true,property="parentId",column="parent_id"),
            @org.apache.ibatis.annotations.Result(property="isButton",column="is_button"),
    })
    @Select("select * from sys_permission")
    List<Permission> findAll();

}
