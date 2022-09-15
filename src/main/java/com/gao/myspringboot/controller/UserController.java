package com.gao.myspringboot.controller;

import com.gao.myspringboot.pojo.User;
import com.gao.myspringboot.service.Impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserServiceImpl userService;

    //用户自己修改名字
    //接收前端传来的字符串
//    @RequestMapping("/getStringParam/{newname}")
    @GetMapping("/getStringParam/{newname}")
    public String getStringParam(@PathVariable("newname") String newName, HttpSession session) {
        //从session中拿到用户，修改session的姓名
        User loginUser = (User) session.getAttribute("loginUser");
        loginUser.setUserName(newName);
        //修改数据库中的名字
        userService.updateUser(loginUser);
        return "redirect:/main.html";
    }

    //前往用户修改信息页面
    @GetMapping("/toUser")
    public String toUser(HttpSession session, Model model) {
        //从session中拿到用户，修改session的姓名
        User loginUser = (User) session.getAttribute("loginUser");
        model.addAttribute("loginUser", loginUser);

        model.addAttribute("maxNewWordsNumList", userService.getMaxNewWordsNumByUserId(loginUser.getUserId()));
        model.addAttribute("maxReviewedWordsNumList", userService.getMaxReviewedWordsNumByUserId(loginUser.getUserId()));
        return "user/user";
    }

    //用户修改信息
    @PostMapping("/userUpdate/{userId}")
    public String userUpdate(@PathVariable("userId") Integer userId, User user, HttpSession session, Model model) {
        //修改信息
        userService.updateUser(user);
        //设置session
        session.setAttribute("loginUser", userService.queryUserById(userId));
        return "redirect:/main.html";
    }
}
