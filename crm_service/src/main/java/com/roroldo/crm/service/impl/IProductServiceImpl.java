package com.roroldo.crm.service.impl;

import com.github.pagehelper.PageHelper;
import com.roroldo.crm.dao.IOrdersDao;
import com.roroldo.crm.dao.IProductDao;
import com.roroldo.crm.domain.Product;
import com.roroldo.crm.service.IProductService;
import com.roroldo.crm.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;

/**
 * IProductService 实现类
 * @author 落霞不孤
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class IProductServiceImpl implements IProductService {
    @Autowired
    private IProductDao iProductDao;

    @Autowired
    private IOrdersDao iOrdersDao;

    @Override
    public List<Product> findAllByPage(Integer pageNum, Integer pageSize, String searchProduct) {
        if (searchProduct != null && searchProduct.trim().length() > 0) {
            PageHelper.startPage(pageNum, pageSize);
            return iProductDao.findAllByPageAndProductName(searchProduct);
        }
        PageHelper.startPage(pageNum, pageSize);
        return iProductDao.findAllByPage();
    }

    @Override
    public void save(Product product) {
        iProductDao.save(product);
    }

    @Override
    public Product findById(Integer id) {
        Product product = iProductDao.findById(id);
        Date date = product.getDepartureTime();
        product.setDepartureTimeStr(DateUtils.dateToString(date, "yyyy-MM-dd HH:mm:ss"));
        return product;
    }

    @Override
    public void update(Product product) {
        iProductDao.update(product);
    }

    @Override
    public void updateStatus(List<Integer> ids) {
        for (Integer id : ids) {
            Product product = findById(id);
            if (product.getProductStatus() == 0) {
                iProductDao.updateStatus(id, 1);
            } else {
                iProductDao.updateStatus(id, 0);
            }
        }
    }

    @Override
    public void deleteById(Integer pid) {
        List<Integer> orderList = iOrdersDao.findByPid(pid);
        iOrdersDao.deleteByPid(pid);
        for (Integer oid : orderList) {
            iOrdersDao.deleteFromOrderAndTraveller(oid);
        }
        iProductDao.deleteById(pid);
    }
}
