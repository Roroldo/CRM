package com.roroldo.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.roroldo.crm.dao.IUserDao;
import com.roroldo.crm.domain.Permission;
import com.roroldo.crm.domain.Role;
import com.roroldo.crm.domain.UserInfo;
import com.roroldo.crm.service.IUserService;
import com.roroldo.crm.utils.BCryptPasswordEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户业务的实现
 * @author 落霞不孤
 */
@Service("userService")
@Transactional(rollbackFor = Exception.class)
public class IUserServiceImpl implements IUserService {
    @Autowired
    private IUserDao iUserDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = iUserDao.findByUsername(username);
        // 将自己的用户对象封装为 UserDetails
        // {noop} 表示密码未加密
        // return new User(userInfo.getUsername(), "{noop}" + userInfo.getPassword(), getAuthority(userInfo.getRoles()));
        return new User(userInfo.getUsername(), userInfo.getPassword(), userInfo.getStatus() != 0,
        true, true, true, getAuthority(userInfo.getRoles()));
    }

    // 返回一个装着角色描述的 List 集合
    private List<SimpleGrantedAuthority> getAuthority(List<Role> roleList) {
        List<SimpleGrantedAuthority> list = new ArrayList<>();
        for (Role role : roleList) {
            list.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleName().toUpperCase()));
        }
        return list;
    }

    @Override
    public List<UserInfo> findAll(int pageNum, int pageSize, String searchUser) {
        PageHelper.startPage(pageNum, pageSize);
        if (searchUser != null && searchUser.trim().length() != 0) {
            return iUserDao.findAllBySearchUser(searchUser);
        }
        return iUserDao.findAll();
    }

    @Override
    public void save(UserInfo user) {
        user.setPassword(BCryptPasswordEncoderUtils.encodePassword(user.getPassword()));
        iUserDao.save(user);
    }

    @Override
    public UserInfo findById(Integer id) {
        return iUserDao.findById(id);
    }

    @Override
    public List<Role> findOtherRoles(Integer userId) {
        return iUserDao.findOtherRoles(userId);
    }

    @Override
    public void addRoleToUser(Integer userId, List<Integer> roleIds) {
        for (Integer roleId : roleIds) {
            iUserDao.addRoleToUser(userId, roleId);
        }
    }

    @Override
    public void deleteById(Integer id) {
        iUserDao.deleteById(id);
        iUserDao.deleteFromUserRoleByUserId(id);
    }

    @Override
    public String updatePassword(Integer id, String oldPassword, String newPassword1, String newPassword2) {
        UserInfo user = iUserDao.findById(id);
        if (BCryptPasswordEncoderUtils.bCryptPasswordEncoder.matches(oldPassword, user.getPassword())) {
            if ((newPassword1.trim().length() > 0 && newPassword2.trim().length() > 0)
            && (newPassword1.equals(newPassword2))) {
                iUserDao.updatePassword(id, BCryptPasswordEncoderUtils.encodePassword(newPassword1));
                return "密码修改成功! 请退出并重新登录...";
            }
            return "两次输入密码不一致";
        } else {
            return "旧密码输入错误";
        }
    }

    @Override
    public UserInfo findByUsername(String username) {
        return iUserDao.findByUsername(username);
    }

    @Override
    public void updateUser(Integer uid, UserInfo userInfo) {
        if (userInfo.getPassword() != null && userInfo.getPassword().trim().length() > 0) {
            userInfo.setPassword(BCryptPasswordEncoderUtils.encodePassword(userInfo.getPassword()));
            iUserDao.updateUserWithPassword(uid, userInfo);
        } else {
            iUserDao.updateUserWithNotPassword(uid, userInfo);
        }
    }
}
