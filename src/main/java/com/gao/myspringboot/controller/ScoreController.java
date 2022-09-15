package com.gao.myspringboot.controller;


import com.gao.myspringboot.pojo.Score;
import com.gao.myspringboot.pojo.User;
import com.gao.myspringboot.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class ScoreController {
    @Autowired
    private ScoreService scoreService;

    @GetMapping("/viewScore")
    public String viewScore(Model model, HttpSession session) {
        User loginUser = (User) session.getAttribute("loginUser");
        List<Score> scoreList = scoreService.getScoreByUserId(loginUser.getUserId());
        model.addAttribute("scoreList", scoreList);
        return "user/score/score";
    }
}
