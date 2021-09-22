package com.roroldo.crm.dao;

import com.roroldo.crm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 旅客操作
 * @author 落霞不孤
 */
public interface ITravellerDao {
    /**
     * 根据订单 id 查找旅客
     * @param ordersId 订单 id
     * @return 旅客
     */
    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId = #{ordersId})")
    List<Traveller> findByOrdersId(String ordersId);
}
