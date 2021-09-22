package com.roroldo.crm.dao;

import com.roroldo.crm.domain.Role;
import com.roroldo.crm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 用户操作
 * @author 落霞不孤
 */
public interface IUserDao {

    /**
     * 根据 username 查找用户
     * @param username username
     * @return 用户
     */
    @Select("select * from users where username = #{username}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username",  column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            @Result(property = "roles", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "com.roroldo.crm.dao.IRoleDao.findByUserId"))
    })
    UserInfo findByUsername(String username);

    /**
     * 根据用户名查找所有的用户
     * @param searchUser 搜索的用户名
     * @return 用户名查找所有的用户
     */
    @Select("select * from users where username like CONCAT('%', #{searchUser}, '%')")
    List<UserInfo> findAllBySearchUser(String searchUser);

    /**
     * 查询所有的用户
     * @return 用户
     */
    @Select("select * from users")
    List<UserInfo> findAll();

    /**
     * 添加用户
     * @param user user
     */
    @Insert("insert into users(username, password, email, phoneNum, status) values(#{username}, #{password}, #{email}, #{phoneNum}, #{status})")
    void save(UserInfo user);

    /**
     * 根据 id 查找用户
     * @param id id
     * @return 用户
     */
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "username",  column = "username"),
            @Result(property = "email", column = "email"),
            @Result(property = "password", column = "password"),
            @Result(property = "phoneNum", column = "phoneNum"),
            @Result(property = "status", column = "status"),
            // 根据用户 id 查找出角色，再根据角色 id 查找出权限
            @Result(property = "roles", column = "id", javaType = java.util.List.class,
                    many = @Many(select = "com.roroldo.crm.dao.IRoleDao.findByUserId"))
    })
    UserInfo findById(Integer id);

    /**
     * 查找用户可以添加的角色
     * @param userId 用户id
     * @return 可以添加的角色
     */
    @Select("select * from role where id not in (select roleId from users_role where userId = #{userId})")
    List<Role> findOtherRoles(Integer userId);

    /**
     * 用户添加角色
     * @param userId 用户 id
     * @param roleId 角色 id
     */
    @Insert("insert into users_role(userId, roleId) values(#{userId}, #{roleId})")
    void addRoleToUser(@Param("userId") Integer userId, @Param("roleId") Integer roleId);

    /**
     * 删除用户以及权限
     * @param id 用户id
     */
    @Delete("delete from users where id = #{id}")
    void deleteById(Integer id);

    /**
     * 根据用户 id 删除用户角色表
     * @param userId 用户id
     */
    @Delete("delete from users_role where userId = #{userId}")
    void deleteFromUserRoleByUserId(Integer userId);

    /**
     * 根据角色 id 删除用户角色表
     * @param roleId 角色id
     */
    @Delete("delete from users_role where roleId = #{roleId}")
    void deleteFromUserRoleByRoleId(int roleId);

    /**
     * 更新密码
     * @param id
     * @param newPassword
     */
    @Update("update users set password = #{newPassword} where id = #{id}")
    void updatePassword(@Param("id") Integer id, @Param("newPassword") String newPassword);

    /**
     * 更新用户（密码也更新）
     * @param uid
     * @param userInfo
     */
    @Update("update users set username=#{userInfo.username}, password=#{userInfo.password}," +
            "email=#{userInfo.email}, phoneNum=#{userInfo.phoneNum}, status=#{userInfo.status} where id = #{uid} ")
    void updateUserWithPassword(@Param("uid") Integer uid, @Param("userInfo") UserInfo userInfo);

    /**
     * 更新用户（密码不更新）
     * @param uid
     * @param userInfo
     */
    @Update("update users set username=#{userInfo.username}, " +
            "email=#{userInfo.email}, phoneNum=#{userInfo.phoneNum}, status=#{userInfo.status} where id = #{uid} ")
    void updateUserWithNotPassword(@Param("uid") Integer uid, @Param("userInfo") UserInfo userInfo);
}
