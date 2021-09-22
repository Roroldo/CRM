package com.roroldo.crm.service;

import com.roroldo.crm.domain.Product;

import java.text.ParseException;
import java.util.List;

/**
 * 产品业务
 * @author 落霞不孤
 */
public interface IProductService {
    List<Product> findAllByPage(Integer pageNum, Integer pageSize, String searchProduct);

    void save(Product product);

    Product findById(Integer id) throws ParseException;

    void update(Product product);

    void updateStatus(List<Integer> ids);

    void deleteById(Integer pid);
}
