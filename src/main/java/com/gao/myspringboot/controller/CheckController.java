package com.gao.myspringboot.controller;


import com.gao.myspringboot.pojo.Check;
import com.gao.myspringboot.pojo.User;
import com.gao.myspringboot.service.CheckService;
import com.gao.myspringboot.service.UserService;
import com.gao.myspringboot.util.ScoreUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CheckController {
    @Autowired
    CheckService checkService;
    @Autowired
    UserService userService;
    @Autowired
    ScoreUtils scoreUtils;

    @GetMapping("/viewCheck")
    public String viewCheck(Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        List<Check> checkList = checkService.queryCheckByUserId(loginUser.getUserId());
        model.addAttribute("checkList", checkList);
        model.addAttribute("isTodayChecked", checkService.isCheckedToday(loginUser.getUserId()));
        return "user/check/check";
    }


    @GetMapping("/toCheck")
    public String toCheck(Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        scoreUtils.addCheck(loginUser.getUserId());
        // 更新session中的用户信息，防止积分显示不一致
        session.setAttribute("loginUser", userService.queryUserById(loginUser.getUserId()));
        return "redirect:/viewCheck";
    }

}
