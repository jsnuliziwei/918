package com.gao.myspringboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gao.myspringboot.mapper.ScoreMapper;
import com.gao.myspringboot.mapper.UserMapper;
import com.gao.myspringboot.pojo.Score;
import com.gao.myspringboot.service.ScoreService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class ScoreServiceImpl extends ServiceImpl<ScoreMapper, Score> implements ScoreService {
    @Autowired
    private ScoreMapper scoreMapper;
    @Autowired
    private UserMapper userMapper;

    @Override
    public List<Score> getScoreByUserId(int userId) {
        QueryWrapper<Score> queryWrapper = new QueryWrapper<Score>();
        queryWrapper.lambda()
                .eq(Score::getUserId, userId);
        return scoreMapper.selectList(queryWrapper);
    }

    @Override
    public int addScore(int userId, int scoreValue, String content) {
        Score score = new Score();
        score.setUserId(userId);
        score.setScore(scoreValue);
        score.setContent(content);
        score.setDate(new Timestamp(new Date().getTime()));
        int sc_now = userMapper.queryUserById(userId).getScore() + scoreValue;
        score.setScoreSum(sc_now);
        userMapper.updateUserScore(userId, sc_now);
        scoreMapper.insert(score);
        return 0;
    }

    @Override
    public boolean isGetScoreTodayByContent(int userId, String content) {
        Score score = scoreMapper.queryLastScoreByContent(userId, content);
        if (score != null && DateUtils.isSameDay(new Date(score.getDate().getTime()), new Date())) return true;
        return false;
    }

}
