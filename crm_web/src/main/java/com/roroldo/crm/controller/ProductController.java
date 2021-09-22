package com.roroldo.crm.controller;

import com.github.pagehelper.PageInfo;
import com.roroldo.crm.domain.Product;
import com.roroldo.crm.service.IProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 * 产品控制类
 * @author 落霞不孤
 */
@Controller
@Slf4j
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private IProductService iProductService;

    @RequestMapping("/findAll.do")
    @RolesAllowed({"ADMIN", "USER"})
    public ModelAndView findAll(@RequestParam(value = "pageNum", required = true, defaultValue = "1")Integer pageNum,
                                @RequestParam(value = "pageSize", required = true, defaultValue = "4")Integer pageSize,
                                @RequestParam(value = "searchProduct", required = false, defaultValue = "") String searchProduct) {
        ModelAndView modelAndView = new ModelAndView();
        List<Product> productList = iProductService.findAllByPage(pageNum, pageSize, searchProduct);
        PageInfo<Product> pageInfo = new PageInfo<>(productList);
        if (searchProduct != null) {
            modelAndView.addObject("searchProduct", searchProduct);
        }
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("product-list");
        return modelAndView;
    }

    @RequestMapping("/save.do")
    @RolesAllowed({"ADMIN", "USER"})
    public String save(Product product) {
        iProductService.save(product);
        return "redirect:/product/findAll.do";
    }


    @RequestMapping("/findById.do")
    @RolesAllowed({"ADMIN", "USER"})
    public ModelAndView findById(@RequestParam("id") Integer id) throws ParseException {
        ModelAndView modelAndView = new ModelAndView();
        Product product = iProductService.findById(id);
        modelAndView.addObject("product", product);
        modelAndView.setViewName("product-update");
        return modelAndView;
    }

    @RequestMapping("/update.do")
    @RolesAllowed({"ADMIN", "USER"})
    public String update(Product product) {
        iProductService.update(product);
        return "redirect:/product/findAll.do";
    }

    @RequestMapping("/updateStatus.do")
    @RolesAllowed({"ADMIN", "USER"})
    public String updateStatus(@RequestParam("ids") ArrayList<Integer> ids) {
        iProductService.updateStatus(ids);
        return "redirect:/product/findAll.do";
    }

    @RequestMapping("/deleteById.do")
    @RolesAllowed({"ADMIN"})
    public String deleteById(@RequestParam("id") Integer pid) {
        iProductService.deleteById(pid);
        return "redirect:/product/findAll.do";
    }
}
