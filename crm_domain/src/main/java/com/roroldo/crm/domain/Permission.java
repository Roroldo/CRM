package com.roroldo.crm.domain;

import lombok.Data;

import java.util.List;

/**
 * 权限
 * @author 落霞不孤
 */
@Data
public class Permission {
    private String id;
    private String permissionName;
    private String url;
    private List<Role> roles;
}
