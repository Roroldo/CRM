package com.roroldo.crm.controller;

import com.github.pagehelper.PageInfo;
import com.roroldo.crm.domain.Permission;
import com.roroldo.crm.domain.Role;
import com.roroldo.crm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.ArrayList;
import java.util.List;

/**
 * 角色控制器
 * @author 落霞不孤
 */
@Controller
@RequestMapping("/role")
@RolesAllowed("ADMIN")
public class RoleController {
    @Autowired
    private IRoleService iRoleService;


    @RequestMapping("/findAll.do")
    public ModelAndView findAll(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "4") Integer pageSize) {

        ModelAndView modelAndView = new ModelAndView();
        List<Role> roleList = iRoleService.findAll(pageNum, pageSize);
        PageInfo<Role> pageInfo = new PageInfo<>(roleList);
        modelAndView.setViewName("role-list");
        modelAndView.addObject("pageInfo", pageInfo);
        return modelAndView;
    }

    @RequestMapping("/save.do")
    public String save(Role role) {
        iRoleService.save(role);
        return "redirect:/role/findAll.do";
    }

    @RequestMapping("/findById")
    public ModelAndView findById(@RequestParam(value = "id", required = true) Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        Role role = iRoleService.findById(id);
        modelAndView.addObject("role", role);
        modelAndView.setViewName("role-show");
        return modelAndView;
    }

    @RequestMapping("/deleteById")
    public String deleteById(Integer id) {
        iRoleService.deleteById(id);
        return "redirect:/role/findAll.do";
    }

    @RequestMapping("/findRoleByIdAndAllPermissions")
    public ModelAndView findRoleByIdAndAllPermissions(@RequestParam("id")Integer roleId) {
        ModelAndView modelAndView = new ModelAndView();
        Role role = iRoleService.findById(roleId);
        modelAndView.addObject("role", role);
        List<Permission> permissionList = iRoleService.findOtherPermissions(roleId);
        modelAndView.addObject("permissionList", permissionList);
        modelAndView.setViewName("permission-role-add");
        return modelAndView;
    }

    @RequestMapping("/addPermissionToRole")
    public String addPermissionToRole(@RequestParam("roleId")Integer roleId, @RequestParam("ids") ArrayList<Integer> permissionIds) {
        iRoleService.addPermissionToRole(roleId, permissionIds);
        return "redirect:/role/findAll.do";
    }
}
