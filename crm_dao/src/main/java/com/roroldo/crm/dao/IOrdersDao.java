package com.roroldo.crm.dao;

import com.roroldo.crm.domain.Orders;
import com.roroldo.crm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 订单操作
 * @author 落霞不孤
 */
public interface IOrdersDao {

    /**
     * 分页查找所有的订单
     * @return 所有的订单
     */
    @Select("select * from orders")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "product", column = "productId", javaType = Product.class,
                one = @One(select = "com.roroldo.crm.dao.IProductDao.findById"))
    })
    List<Orders> findAllByPage();

    /**
     * 分页查找满足条件的所有订单
     * @param searchOrders 订单名字
     * @return 所有的订单
     */
    @Select("select * from orders where orderNum like CONCAT('%', #{searchOrders}, '%')")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "product", column = "productId", javaType = Product.class,
                    one = @One(select = "com.roroldo.crm.dao.IProductDao.findById"))
    })
    List<Orders> findAllByPageAndOrdersDesc(String searchOrders);

    /**
     * 根据 id 查找订单
     * @param id id
     * @return 订单
     */
    @Select("select * from orders where id = #{id}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "product", column = "productId", javaType = Product.class,
                    one = @One(select = "com.roroldo.crm.dao.IProductDao.findById")),
            @Result(property = "travellers", column = "id", many = @Many(
                    select = "com.roroldo.crm.dao.ITravellerDao.findByOrdersId"
            )),
            @Result(property = "member", column = "memberId", one = @One(
                    select = "com.roroldo.crm.dao.IMemberDao.findById"
            ))
    })
    Orders findById(Integer id);

    @Delete("delete from orders where id = #{id}")
    void deleteById(Integer id);

    @Delete("delete from order_traveller where orderId = #{id}")
    void deleteFromOrderAndTraveller(Integer id);

    @Delete("delete from orders where productId = #{pid}")
    void deleteByPid(Integer pid);

    @Select("select id from orders where productId = #{pid}")
    List<Integer> findByPid(Integer pid);
}
