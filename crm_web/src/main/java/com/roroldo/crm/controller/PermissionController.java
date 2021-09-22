package com.roroldo.crm.controller;

import com.github.pagehelper.PageInfo;
import com.roroldo.crm.domain.Permission;
import com.roroldo.crm.domain.Role;
import com.roroldo.crm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

/**
 * 权限控制器
 * @author 落霞不孤
 */
@Controller
@RequestMapping("/permission")
@RolesAllowed("ADMIN")
public class PermissionController {
    @Autowired
    private IPermissionService iPermissionService;

    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "4") Integer pageSize) {
        ModelAndView modelAndView = new ModelAndView();
        List<Permission> permissionList = iPermissionService.findAll(pageNum, pageSize);
        PageInfo<Permission> pageInfo = new PageInfo<>(permissionList);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("permission-list");
        return modelAndView;
    }

    @RequestMapping("/save.do")
    public String save(Permission permission) {
        iPermissionService.save(permission);
        return "redirect:/permission/findAll.do";
    }

    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Permission permission = iPermissionService.findById(id);
        modelAndView.addObject("permission", permission);
        modelAndView.setViewName("permission-show");
        return modelAndView;
    }

    @RequestMapping("/deleteById.do")
    public String deleteById(@RequestParam(value = "id") Integer id) {
        iPermissionService.deleteById(id);
        return "redirect:/permission/findAll.do";
    }

    @RequestMapping("/updateByIdUI.do")
    public ModelAndView updateByIdUI(@RequestParam(value = "id") Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Permission permission = iPermissionService.findById(id);
        modelAndView.addObject("permission", permission);
        modelAndView.setViewName("permission-update");
        return modelAndView;
    }

    @RequestMapping("/updateById.do")
    public String updateById(Permission permission) {
        iPermissionService.updateById(permission);
        return "redirect:/permission/findAll.do";
    }
}
