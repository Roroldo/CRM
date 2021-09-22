package com.roroldo.crm.service;


import com.roroldo.crm.domain.Permission;
import com.roroldo.crm.domain.Role;

import java.util.List;

public interface IRoleService {
    List<Role> findAll(int pageNum, int pageSize);

    void save(Role role);

    Role findById(int id);

    void deleteById(int id);

    List<Permission> findOtherPermissions(Integer roleId);

    void addPermissionToRole(Integer roleId, List<Integer> permissionIds);
}
