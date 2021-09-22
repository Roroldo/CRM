package com.roroldo.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.roroldo.crm.dao.IPermissionDao;
import com.roroldo.crm.domain.Permission;
import com.roroldo.crm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class IPermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao iPermissionDao;

    @Override
    public List<Permission> findAll(int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return iPermissionDao.findAll();
    }

    @Override
    public void save(Permission permission) {
        iPermissionDao.save(permission);
    }

    @Override
    public Permission findById(int id) {
        return iPermissionDao.findById(id);
    }

    @Override
    public void deleteById(int id) {
        iPermissionDao.deleteFromRolePermission(id);
        iPermissionDao.deleteFromPermission(id);
    }

    @Override
    public void updateById(Permission permission) {
        iPermissionDao.update(permission);
    }
}
