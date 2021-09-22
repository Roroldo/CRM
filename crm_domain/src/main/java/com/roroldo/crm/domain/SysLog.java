package com.roroldo.crm.domain;

import com.roroldo.crm.utils.DateUtils;
import lombok.Data;

import java.util.Date;

/**
 * 系统日志实体类
 * @author 落霞不孤
 */
@Data
public class SysLog {
    private String id;
    private Date visitTime;
    private String visitTimeStr;
    private String username;
    private String ip;
    private String url;
    private Long executionTime;
    private String method;

    public String getVisitTimeStr() {
        if (visitTime != null && visitTimeStr == null) {
            visitTimeStr = DateUtils.dateToString(visitTime, "yyyy年MM月dd日 HH时mm分ss秒");
            return visitTimeStr;
        }
        return visitTimeStr;
    }
}
