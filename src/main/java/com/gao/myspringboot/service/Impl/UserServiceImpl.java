package com.gao.myspringboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gao.myspringboot.mapper.UserMapper;
import com.gao.myspringboot.pojo.User;
import com.gao.myspringboot.service.PrivilegeService;
import com.gao.myspringboot.service.ScoreService;
import com.gao.myspringboot.service.UserService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    UserMapper userMapper;
    @Autowired
    PrivilegeService privilegeService;

    @Override
    public User queryUserById(int userId) {
        return userMapper.queryUserById(userId);
    }

    @Override
    public User loginByEmailAndPassword(User user){
        return userMapper.loginByEmailAndPassword(user);
    }

    @Override
    public User AdminLogin(User user) {
        return userMapper.AdminLogin(user);
    }

    @Override
    public int addUser(User user) {
        user.setLastLoginTime(new Timestamp(0));
        userMapper.insert(user);
        return 0;
    }

    @Override
    public int deleteUser(Integer userId) {
        return userMapper.deleteUser(userId);
    }

    /**
     * 用户自己修改
     *
     * @param user
     * @return
     */
    @Override
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }

    /**
     * 管理员修改,积分变化不记录入积分表
     *
     * @return
     */
    @Override
    public int updateUserFromAdmin(User user) {
        userMapper.updateUser(user);
        userMapper.updateUserScore(user.getUserId(), user.getScore());
        return 0;
    }

    @Override
    public int updateLastLoginTime(int userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.lambda()
                .eq(User::getUserId, userId);
        User user = userMapper.selectOne(queryWrapper);

        Timestamp now = new Timestamp(new Date().getTime());
        user.setLastLoginTime(now);
        userMapper.update(user, queryWrapper);
        return 0;
    }


    @Override
    public int addNumWordsToday(int userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.lambda()
                .eq(User::getUserId, userId);
        User user = userMapper.selectOne(queryWrapper);

        user.addOneNumWordsToday();
        userMapper.update(user, queryWrapper);
        return 0;
    }

    @Override
    public int addOneNumWordsReviewToday(int userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.lambda()
                .eq(User::getUserId, userId);
        User user = userMapper.selectOne(queryWrapper);

        user.addOneNumWordsReviewToday();
        userMapper.update(user, queryWrapper);
        return 0;
    }


    @Override
    public boolean isNewFinishedToday(int userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.lambda()
                .eq(User::getUserId, userId);
        User user = userMapper.selectOne(queryWrapper);
        return user.isNewFinishedToday();
    }

    @Override
    public boolean isReviewFinishedToday(int userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.lambda()
                .eq(User::getUserId, userId);
        User user = userMapper.selectOne(queryWrapper);
        return user.isReviewFinishedToday();
    }

    @Override
    public Date getLastLoginTime(int userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.lambda()
                .eq(User::getUserId, userId);
        User user = userMapper.selectOne(queryWrapper);
        Timestamp lastLoginTime = user.getLastLoginTime();
        if (lastLoginTime == null){
            return new Date(0);
        }
        else return new Date(lastLoginTime.getTime());
    }

    @Override
    public boolean isTodayLogin(int userId) {

        return DateUtils.isSameDay(getLastLoginTime(userId), new Date());
    }

    @Override
    public int resetNumWordsMaxReviewAndNumWordsMaxReview(int userId) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>();
        queryWrapper.lambda()
                .eq(User::getUserId, userId);
        User user = userMapper.selectOne(queryWrapper);

        user.resetNumWordsReviewToday();
        user.resetNumWordsToday();
        userMapper.update(user, queryWrapper);
        return 0;
    }

    @Autowired
    private ScoreService scoreService;

    /**
     * 更新用户的分数
     *
     * @param userId
     * @param score
     * @return
     */
    @Override
    public int updateUserScore(int userId, int score, String content) {
        scoreService.addScore(userId, score, content);
        return 0;
    }

    @Override
    public List<Integer> getMaxNewWordsNumByUserId(int userId) {
        User user = userMapper.queryUserById(userId);
        return privilegeService.getMaxNewWordsNumByScore(user.getScore());
    }

    @Override
    public List<Integer> getMaxReviewedWordsNumByUserId(int userId) {
        User user = userMapper.queryUserById(userId);
        return privilegeService.getMaxReviewedWordsNumByScore(user.getScore());
    }
}
