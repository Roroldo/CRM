package com.roroldo.crm.dao;

import com.roroldo.crm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 产品 dao
 * @author 落霞不孤
 */
public interface IProductDao {
    /**
     * 分页查询所有的产品
     * @return 所有的产品
     */
    @Select("select * from product")
    List<Product> findAllByPage();

    /**
     * 保存产品信息
     * @param product 产品
     */
    @Insert("insert into product(productNum, productName, cityName, departureTime, productPrice, productDesc, productStatus) values(#{productNum}, #{productName}, #{cityName}, #{departureTime}, #{productPrice}, #{productDesc}, #{productStatus})")
    void save(Product product);

    /**
     * 根据产品名称查询产品
     * @param searchProduct 搜索的产品信息
     * @return 符合的产品
     */
    @Select("select * from product where productName like  CONCAT('%', #{searchProduct}, '%')")
    List<Product> findAllByPageAndProductName(String searchProduct);

    /**
     * 根据id查询产品信息
     * @param id 产品id
     * @return 产品信息
     */
    @Select("select * from product where id = #{id}")
    Product findById(Integer id);

    /**
     * 更新产品的信息
     * @param product product
     */
    @Update("update product " +
            "set productNum = #{productNum}, productName = #{productName}," +
                "cityName = #{cityName}, departureTime = #{departureTime}," +
                "productPrice = #{productPrice}, productDesc = #{productDesc}, productStatus = #{productStatus} " +
            "where id = #{id}")
    void update(Product product);

    /**
     * 更新产品状态
     * @param id 产品 id
     * @param productStatus 状态
     */
    @Update("update product set productStatus = #{productStatus} where id = #{id}")
    void updateStatus(@Param("id") Integer id, @Param("productStatus") int productStatus);

    @Delete("delete from product where id = #{pid}")
    void deleteById(Integer pid);
}
