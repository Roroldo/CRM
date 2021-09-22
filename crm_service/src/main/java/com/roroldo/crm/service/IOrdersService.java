package com.roroldo.crm.service;

import com.roroldo.crm.domain.Orders;

import java.util.List;

/**
 * 订单业务
 * @author 落霞不孤
 */
public interface IOrdersService {
    List<Orders> findAllByPage(Integer pageNum, Integer pageSize, String searchOrders);

    Orders findById(Integer id);

    void deleteById(Integer id);
}
