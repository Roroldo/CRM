package com.roroldo.crm.service;

import com.roroldo.crm.domain.Role;
import com.roroldo.crm.domain.UserInfo;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface IUserService extends UserDetailsService {
    List<UserInfo> findAll(int pageNum, int pageSize, String searchUser);

    void save(UserInfo user);

    UserInfo findById(Integer id);

    List<Role> findOtherRoles(Integer userId);

    void addRoleToUser(Integer userId, List<Integer> roleIds);

    void deleteById(Integer id);

    String updatePassword(Integer id, String oldPassword, String newPassword1, String newPassword2);

    UserInfo findByUsername(String username);

    void updateUser(Integer uid, UserInfo userInfo);
}
