package com.gao.myspringboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gao.myspringboot.pojo.Score;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ScoreService extends IService<Score> {
    List<Score> getScoreByUserId(int userId);

    int addScore(int userId, int scoreValue, String content);

    boolean isGetScoreTodayByContent(int userId, String content);
}
