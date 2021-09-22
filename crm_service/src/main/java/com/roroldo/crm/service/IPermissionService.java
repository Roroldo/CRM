package com.roroldo.crm.service;

import com.roroldo.crm.domain.Permission;

import java.util.List;

public interface IPermissionService {

    List<Permission> findAll(int pageNum, int pageSize);

    void save(Permission permission);

    Permission findById(int id);

    void deleteById(int id);

    void updateById(Permission permission);
}
