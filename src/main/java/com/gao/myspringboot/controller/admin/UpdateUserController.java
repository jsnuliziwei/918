package com.gao.myspringboot.controller.admin;

import com.gao.myspringboot.pojo.User;
import com.gao.myspringboot.service.Impl.UserServiceImpl;
import com.gao.myspringboot.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class UpdateUserController {
    @Autowired
    UserServiceImpl userService;
    @Autowired
    PrivilegeService privilegeService;
    /**
     * 查看用户
     */
    @GetMapping("/admin/viewUser")
    public String viewUser(Model model) {
        List<User> user = userService.list();
        model.addAttribute("user", user);
        return "admin/User-View";
    }


    /**
     * 修改用户
     */
    @GetMapping("/admin/toUpdateUser/{userId}")
    public String toUpdateUser(@PathVariable("userId") Integer userId, Model model) {

        User user = userService.queryUserById(userId);
        model.addAttribute("user", user);
        model.addAttribute("maxNewWordsNumList", userService.getMaxNewWordsNumByUserId(userId));
        model.addAttribute("maxReviewedWordsNumList", userService.getMaxReviewedWordsNumByUserId(userId));
        return "admin/User-Update";
    }

    //进行修改提交，回到view页面
    @PostMapping("/admin/updateUser/{userId}")
    public String updateUser(@PathVariable("userId") Integer userId, User user) {
        userService.updateUserFromAdmin(user);
        return "redirect:/admin/viewUser";
    }

    /**
     * 删除用户
     */

    @RequestMapping("/admin/deleteUser/{userId}")
    public String deleteUser(@PathVariable("userId") Integer userId) {
        userService.deleteUser(userId);
        return "redirect:/admin/viewUser";
    }

    /**
     * 增加用户
     */
    @GetMapping("/admin/toAddUser")
    public String toAddUser(Model model) {
        model.addAttribute("maxNewWordsNumList", privilegeService.getMaxNewWordsNumByScore(Integer.MAX_VALUE));
        model.addAttribute("maxReviewedWordsNumList", privilegeService.getMaxNewWordsNumByScore(Integer.MAX_VALUE));
        return "admin/User-Add";
    }

    @PostMapping("/admin/addUser")
    public String addUser(User user) {
        userService.addUser(user);
        return "redirect:/admin/viewUser";
    }
}