package com.roroldo.crm.domain;


import lombok.Data;

/**
 * 会员
 * @author 落霞不孤
 */
@Data
public class Member {
    private String id;
    private String name;
    private String nickname;
    private String phoneNum;
    private String email;
}
