package com.roroldo.crm.service.impl;


import com.github.pagehelper.PageHelper;
import com.roroldo.crm.dao.ISysLogDao;
import com.roroldo.crm.domain.Orders;
import com.roroldo.crm.domain.SysLog;
import com.roroldo.crm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ISysLogServiceImpl implements ISysLogService {
    @Autowired
    private ISysLogDao iSysLogDao;

    @Override
    public void save(SysLog sysLog) {
        iSysLogDao.save(sysLog);
    }

    @Override
    public List<SysLog> findAllByPage(Integer pageNum, Integer pageSize, String searchUser) {
        if (searchUser != null && searchUser.trim().length() != 0) {
            searchUser = "%" + searchUser + "%";
            PageHelper.startPage(pageNum, pageSize);
            return iSysLogDao.findAllByUsername(searchUser);
        }
        PageHelper.startPage(pageNum, pageSize);
        return iSysLogDao.findAll();
    }

    @Override
    public void deleteByIds(List<Integer> ids) {
        for (Integer id : ids) {
            iSysLogDao.deleteById(id);
        }
    }
}
