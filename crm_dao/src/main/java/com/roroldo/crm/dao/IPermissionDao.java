package com.roroldo.crm.dao;

import com.roroldo.crm.domain.Permission;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 权限 dao
 * @author 落霞不孤
 */
public interface IPermissionDao {
    /**
     * 根据角色 id 查找权限
     * @param id 角色id
     * @return 权限
     */
    @Select("select * from permission where id in (select permissionId from role_permission where roleId=#{id})")
    List<Permission> findByRoleId(String id);

    /**
     * 查找所有的权限
     * @return 所有的权限
     */
    @Select("select * from permission")
    List<Permission> findAll();

    @Insert("insert into permission (permissionName, url) values(#{permissionName}, #{url})")
    void save(Permission permission);

    /**
     * 根据 id 查找权限
     * @param id id
     * @return 权限
     */
    @Select("select * from permission where id = #{id}")
    @Results({
            @Result(property = "id", column = "id", id = true),
            @Result(property = "permissionName", column = "permissionName"),
            @Result(property = "url", column = "url"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class,
                many = @Many(select = "com.roroldo.crm.dao.IRoleDao.findByPermissionId"))
    })
    Permission findById(int id);

    @Delete("delete from role_permission where permissionId = #{permissionId}")
    void deleteFromRolePermission(int permissionId);

    @Delete("delete from role where id = #{id}")
    void delete(int id);

    @Delete("delete from permission where id = #{id}")
    void deleteFromPermission(int id);

    @Update("update permission set permissionName=#{permissionName}, url=#{url} where id = #{id}")
    void update(Permission p);
}
