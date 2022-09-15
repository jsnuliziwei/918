package com.gao.myspringboot.util;

import com.gao.myspringboot.mapper.UserMapper;
import com.gao.myspringboot.service.CheckService;
import com.gao.myspringboot.service.ScoreService;
import com.gao.myspringboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class ScoreUtils {
    @Autowired
    private ScoreService scoreService;
    @Autowired
    private UserService userService;
    @Autowired
    CheckService checkService;

    @Value("${scorePerCheck}")
    private int scorePerCheck;

    @Value("${scoreFinishJob}")
    private int scoreFinishJob;

    public void addCheck(int userId) {
        userService.updateUserScore(userId, scorePerCheck, "签到" + "+" + scorePerCheck);
        checkService.addCheck(userId); //在签到表中添加签到记录
    }

    public String addFinishScore(int userId) {
        String content = "完成每日任务" + "+" + scoreFinishJob;
        if (!scoreService.isGetScoreTodayByContent(userId, content))
            userService.updateUserScore(userId, scoreFinishJob, content);
        return content;
    }
}
