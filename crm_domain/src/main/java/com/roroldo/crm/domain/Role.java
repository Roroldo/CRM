package com.roroldo.crm.domain;

import lombok.Data;

import java.util.List;

/**
 * 角色
 * @author 落霞不孤
 */
@Data
public class Role {
    private String id;
    private String roleName;
    private String roleDesc;
    private List<Permission> permissions;
    private List<UserInfo> users;
}
