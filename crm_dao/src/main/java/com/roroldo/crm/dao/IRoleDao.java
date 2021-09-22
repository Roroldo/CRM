package com.roroldo.crm.dao;

import com.roroldo.crm.domain.Permission;
import com.roroldo.crm.domain.Role;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 角色操作
 * @author 落霞不孤
 */
public interface IRoleDao {
    /**
     * 根据用户 id 查找角色以及权限
     * @param userId userId
     * @return 角色
     */
    @Select("select * from role where id in (select roleId from users_role where userId = #{userId})")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class,
            many = @Many(select = "com.roroldo.crm.dao.IPermissionDao.findByRoleId")),
    })
    List<Role> findByUserId(String userId);

    /**
     * 查询所有的角色
     * @return 所有的角色
     */
    @Select("select * from role")
    List<Role> findAll();

    /**
     * 添加角色
     * @param role role
     */
    @Insert("insert into role(roleName, roleDesc) values(#{roleName}, #{roleDesc})")
    void save(Role role);

    /**
     * 根据角色 id 查找权限
     * @param id id
     * @return 角色
     */
    @Select("select * from role where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "roleName", column = "roleName"),
            @Result(property = "roleDesc", column = "roleDesc"),
            @Result(property = "permissions", column = "id", javaType = java.util.List.class,
                many=@Many(select = "com.roroldo.crm.dao.IPermissionDao.findByRoleId"))
    })
    Role findByRoleId(int id);

    /**
     * 根据 id 从 role_permission 删除关联角色
     * @param id id
     */
    @Delete("delete from role_permission where roleId = #{roleId}")
    void deleteFromRolePermission(int id);

    /**
     * 根据 id 删除角色
     * @param id id
     */
    @Delete("delete from role where id = #{id}")
    void deleteById(int id);

    /**
     * 根据权限 id 查找所有的角色
     * @param permissionId 权限 id
     * @return 所有的角色
     */
    @Select("SELECT * FROM role WHERE id IN (SELECT roleId FROM role_permission WHERE permissionId = #{permissionId})")
    List<Role> findByPermissionId(int permissionId);

    /**
     * 查询角色可以添加的权限
     * @param roleId 角色id
     * @return 可以添加的权限
     */
    @Select("select * from permission where id not in (select permissionId from role_permission where roleId = #{roleId})")
    List<Permission> findOtherPermissions(Integer roleId);

    /**
     * 添加权限到角色中
     * @param roleId roleId
     * @param permissionId permissionId
     */
    @Insert("insert into role_permission(roleId, permissionId) values(#{roleId}, #{permissionId})")
    void addPermissionToRole(@Param("roleId") Integer roleId, @Param("permissionId") Integer permissionId);
}
