package com.roroldo.crm.controller;


import com.github.pagehelper.PageInfo;
import com.roroldo.crm.domain.Orders;
import com.roroldo.crm.domain.SysLog;
import com.roroldo.crm.service.IOrdersService;
import com.roroldo.crm.service.ISysLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

/**
 * 系统日志控制器
 * @author 落霞不孤
 */
@Controller
@RequestMapping("/sysLog")
@RolesAllowed("ADMIN")
public class SysLogController {
    @Autowired
    private ISysLogService iSysLogService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(value = "pageNum", required = true, defaultValue = "1")Integer pageNum,
                                @RequestParam(value = "pageSize", required = true, defaultValue = "5")Integer pageSize,
                                @RequestParam(value = "searchUser", required = false, defaultValue = "") String searchUser) {
        ModelAndView modelAndView = new ModelAndView();
        if (searchUser != null && searchUser.trim().length() > 0) {
            modelAndView.addObject("searchUser", searchUser);
        }
        List<SysLog> sysLogList = iSysLogService.findAllByPage(pageNum, pageSize, searchUser);
        PageInfo<SysLog> pageInfo = new PageInfo<>(sysLogList);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("syslog-list");
        return modelAndView;
    }

    @RequestMapping("/deleteByIds.do")
    public String deleteByIds(@RequestParam(value = "ids") ArrayList<Integer> ids) {
        System.out.println(ids.size());
        iSysLogService.deleteByIds(ids);
        return "redirect:/sysLog/findAll.do";
    }
}
