package com.gao.myspringboot.controller;

import com.gao.myspringboot.pojo.User;
//import com.gao.myspringboot.service.Impl.SendEmailImpl;
import com.gao.myspringboot.service.Impl.UserServiceImpl;
import com.gao.myspringboot.util.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController {

    @Autowired
    private UserServiceImpl userService;
    //进行注册
    @PostMapping("/register")
    public String register(User user, Model model) {
        //加入到数据库
        userService.addUser(user);
        System.out.println("增加成功");
        return "index";
    }

}
