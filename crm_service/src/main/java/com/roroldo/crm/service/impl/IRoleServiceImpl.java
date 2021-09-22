package com.roroldo.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.roroldo.crm.dao.IRoleDao;
import com.roroldo.crm.dao.IUserDao;
import com.roroldo.crm.domain.Permission;
import com.roroldo.crm.domain.Role;
import com.roroldo.crm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class IRoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao iRoleDao;

    @Autowired
    private IUserDao iUserDao;

    @Override
    public List<Role> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return iRoleDao.findAll();
    }

    @Override
    public void save(Role role) {
        iRoleDao.save(role);
    }

    @Override
    public Role findById(int id) {
        return iRoleDao.findByRoleId(id);
    }

    @Override
    public void deleteById(int id) {
        // user_role 表
        iUserDao.deleteFromUserRoleByRoleId(id);
        // role_permission 表
        iRoleDao.deleteFromRolePermission(id);
        // role 表
        iRoleDao.deleteById(id);
    }

    @Override
    public List<Permission> findOtherPermissions(Integer roleId) {
        return iRoleDao.findOtherPermissions(roleId);
    }

    @Override
    public void addPermissionToRole(Integer roleId, List<Integer> permissionIds) {
        for (Integer permissionId : permissionIds) {
            iRoleDao.addPermissionToRole(roleId, permissionId);
        }
    }
}
