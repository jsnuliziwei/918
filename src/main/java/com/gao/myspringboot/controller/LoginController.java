package com.gao.myspringboot.controller;

import com.gao.myspringboot.pojo.User;
import com.gao.myspringboot.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class LoginController {


    @Autowired
    private UserServiceImpl userService;

    /**
     * 普通用户登录
     * 登录成功，添加session
     */
    @GetMapping("/login")
    public String login(User user, Model model, HttpSession session) {
        User usr = userService.loginByEmailAndPassword(user);
        if (usr != null) {
            System.out.println("成功登录");
            //设置session
            session.setAttribute("loginUser", usr);

            if (!userService.isTodayLogin(usr.getUserId())) {
                userService.resetNumWordsMaxReviewAndNumWordsMaxReview(usr.getUserId());
            }
            userService.updateLastLoginTime(usr.getUserId());
            return "redirect:/main.html";
        } else {
            model.addAttribute("msg", "密码或账号输入错误");
            return "index";
        }
    }

    /**
     * 管理员用户登录
     */
    @GetMapping("/toAdmin")
    public String toAdmin() {
        return "admin/index";
    }

    @GetMapping("/admin")
    public String AdminLogin(User user, Model model, HttpSession session) {
        User usr = userService.AdminLogin(user);
        if (usr != null) {

            session.setAttribute("loginUser", user);
            System.out.println("管理员成功登录");
            return "redirect:/admin/main";
        } else {
            model.addAttribute("msg", "密码或账号输入错误");
            return "admin/index";

        }
    }

    /**
     * 注销
     */
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "index";
    }

}
