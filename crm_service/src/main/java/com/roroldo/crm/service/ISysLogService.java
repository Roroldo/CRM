package com.roroldo.crm.service;


import com.roroldo.crm.domain.SysLog;

import java.util.List;

public interface ISysLogService {

    void save(SysLog sysLog);

    List<SysLog> findAllByPage(Integer pageNum, Integer pageSize, String searchOrders);

    void deleteByIds(List<Integer> ids);
}
