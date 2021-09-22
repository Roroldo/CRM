package com.roroldo.crm.domain;

import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * 旅客
 * @author 落霞不孤
 */
@Data
public class Traveller {
    private String id;
    private String name;
    private String sex;
    private String phoneNum;
    private Integer credentialsType;
    private String credentialsTypeStr;
    private String credentialsNum;
    private Integer travellerType;
    private String travellerTypeStr;

    public String getCredentialsTypeStr() {
        if (credentialsType == 2) {
            credentialsTypeStr = "军官证";
        } else if (credentialsType == 1) {
            credentialsTypeStr = "护照";
        } else {
            credentialsTypeStr = "身份证";
        }
        return credentialsTypeStr;
    }

    public String getTravellerTypeStr() {
        if (travellerType == 1) {
            travellerTypeStr = "儿童";
        } else {
            travellerTypeStr = "成人";
        }
        return travellerTypeStr;
    }
}
