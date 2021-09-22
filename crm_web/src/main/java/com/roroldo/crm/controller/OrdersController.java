package com.roroldo.crm.controller;


import com.github.pagehelper.PageInfo;
import com.roroldo.crm.domain.Orders;
import com.roroldo.crm.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * 订单管理控制器
 * @author 落霞不孤
 */
@Controller
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private IOrdersService iOrdersService;

    @RequestMapping("/findAll.do")
    @RolesAllowed({"ADMIN", "USER"})
    public ModelAndView findAll(@RequestParam(value = "pageNum", required = true, defaultValue = "1")Integer pageNum,
                                @RequestParam(value = "pageSize", required = true, defaultValue = "4")Integer pageSize,
                                @RequestParam(value = "searchOrders", required = false, defaultValue = "") String searchOrders) {
        ModelAndView modelAndView = new ModelAndView();
        if (searchOrders != null) {
            modelAndView.addObject("searchOrders", searchOrders);
        }
        List<Orders> ordersList = iOrdersService.findAllByPage(pageNum, pageSize, searchOrders);
        PageInfo<Orders> pageInfo = new PageInfo<>(ordersList);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("orders-list");
        return modelAndView;
    }

    @RequestMapping("/findById.do")
    @RolesAllowed({"ADMIN", "USER"})
    public ModelAndView findById(@RequestParam(value = "id")Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Orders orders = iOrdersService.findById(id);
        modelAndView.addObject("orders", orders);
        modelAndView.setViewName("orders-show");
        return modelAndView;
    }


    @RequestMapping("/deleteById.do")
    @RolesAllowed({"ADMIN"})
    public String deleteById(@RequestParam(value = "id") Integer id) {
        iOrdersService.deleteById(id);
        return "redirect:/orders/findAll.do";
    }
}
