package com.roroldo.crm.controller;

import com.github.pagehelper.PageInfo;
import com.roroldo.crm.domain.Role;
import com.roroldo.crm.domain.UserInfo;
import com.roroldo.crm.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * 用户控制类
 * @author 落霞不孤
 */
@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    private IUserService iUserService;

    @RequestMapping("/findAll.do")
    @RolesAllowed({"ADMIN"})
    public ModelAndView findAll(@RequestParam(value = "pageNum", required = false, defaultValue = "1") Integer pageNum,
                                @RequestParam(value = "pageSize", required = false, defaultValue = "4") Integer pageSize,
                                @RequestParam(value = "searchUser", required = false, defaultValue = "") String searchUser) {
        ModelAndView modelAndView = new ModelAndView();
        if (searchUser != null) {
            modelAndView.addObject("searchUser", searchUser);
        }
        List<UserInfo> userList = iUserService.findAll(pageNum, pageSize, searchUser);
        PageInfo<UserInfo> pageInfo = new PageInfo<>(userList);
        modelAndView.addObject("pageInfo", pageInfo);
        modelAndView.setViewName("user-list");
        return modelAndView;
    }

    @RequestMapping("/save.do")
    @RolesAllowed({"ADMIN"})
    public String save(UserInfo user) {
        iUserService.save(user);
        return "redirect:/user/findAll.do";
    }

    @RequestMapping("/updatePassword.do")
    @RolesAllowed({"ADMIN", "USER"})
    public ModelAndView updatePassword(Integer id, String oldPassword, String newPassword1, String newPassword2) {
        ModelAndView modelAndView = new ModelAndView();
        String errorMsg = iUserService.updatePassword(id, oldPassword, newPassword1, newPassword2);
        modelAndView.setViewName("user-update-password");
        modelAndView.addObject("errorMsg", errorMsg);
        return modelAndView;
    }


    @RequestMapping("/findById.do")
    @RolesAllowed({"ADMIN"})
    public ModelAndView findById(Integer id) {
        ModelAndView modelAndView = new ModelAndView();
        UserInfo user = iUserService.findById(id);
        modelAndView.addObject("user", user);
        modelAndView.setViewName("user-show");
        return modelAndView;
    }

    @RequestMapping(value = "/findByIdUI.do", method = RequestMethod.GET, produces = "application/json")
    @RolesAllowed({"ADMIN"})
    @ResponseBody
    public UserInfo findByIdUI(Integer userId) {
        return iUserService.findById(userId);
    }

    @RequestMapping("/updatePasswordUI.do")
    @RolesAllowed({"ADMIN", "USER"})
    public ModelAndView updatePasswordUI(String username, HttpSession session) {
        ModelAndView modelAndView = new ModelAndView();
        UserInfo user = iUserService.findByUsername(username);
        session.setAttribute("user", user);
        modelAndView.setViewName("user-update-password");
        return modelAndView;
    }

    @RequestMapping("/updateUserUI.do")
    @RolesAllowed({"ADMIN"})
    public ModelAndView updateUserUI(Integer uid) {
        ModelAndView modelAndView = new ModelAndView();
        UserInfo updateUser = iUserService.findById(uid);
        modelAndView.addObject("updateUser", updateUser);
        modelAndView.setViewName("user-update");
        return modelAndView;
    }

    @RequestMapping("/updateUser.do")
    @RolesAllowed({"ADMIN"})
    public String updateUser(Integer uid, UserInfo userInfo) {
        iUserService.updateUser(uid, userInfo);
        return "redirect:/user/findAll.do";
    }

    @RequestMapping("/deleteById.do")
    @RolesAllowed({"ADMIN"})
    public String deleteById(Integer userId) {
        iUserService.deleteById(userId);
        return "redirect:/user/findAll.do";
    }

    @RequestMapping("/findUserByIdAndAllRole.do")
    @RolesAllowed({"ADMIN"})
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id", required = true) Integer userId) {
        ModelAndView modelAndView = new ModelAndView();
        UserInfo userInfo = iUserService.findById(userId);
        List<Role> roleList = iUserService.findOtherRoles(userId);
        modelAndView.addObject("user", userInfo);
        modelAndView.addObject("roleList", roleList);
        modelAndView.setViewName("user-role-add");
        return modelAndView;
    }

    @RequestMapping("/addRoleToUser.do")
    @RolesAllowed({"ADMIN"})
    public String addRoleToUser(@RequestParam("userId") Integer userId, @RequestParam("ids") ArrayList<Integer> roleIds) {
        iUserService.addRoleToUser(userId, roleIds);
        return "redirect:/user/findAll.do";
    }
}
