package com.roroldo.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.roroldo.crm.dao.IOrdersDao;
import com.roroldo.crm.domain.Orders;
import com.roroldo.crm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(rollbackFor = Exception.class)
public class IOrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao iOrdersDao;


    @Override
    public List<Orders> findAllByPage(Integer pageNum, Integer pageSize, String searchOrders) {
        PageHelper.startPage(pageNum, pageSize);
        if (searchOrders != null && searchOrders.trim().length() != 0) {
            return iOrdersDao.findAllByPageAndOrdersDesc(searchOrders);
        }
        return iOrdersDao.findAllByPage();
    }

    @Override
    public Orders findById(Integer id) {
        return iOrdersDao.findById(id);
    }

    @Override
    public void deleteById(Integer id) {
        iOrdersDao.deleteById(id);
        iOrdersDao.deleteFromOrderAndTraveller(id);
    }
}
