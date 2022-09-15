package com.gao.myspringboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gao.myspringboot.pojo.User;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public interface UserService extends IService<User> {
    //根据Id查询用户
    User queryUserById(int userId);

    User loginByEmailAndPassword(User user);

    //根据邮箱密码与Role=1登录
    User AdminLogin(User user);

    //增加用户
    int addUser(User user);

    //删除用户
    int deleteUser(Integer userId);

    // 修改用户
    int updateUser(User user);

    public int updateUserFromAdmin(User user);


    int updateLastLoginTime(int userId);

    int addNumWordsToday(int userId);

    int addOneNumWordsReviewToday(int userId);

    boolean isNewFinishedToday(int userId);

    boolean isReviewFinishedToday(int userId);

    Date getLastLoginTime(int userId);

    boolean isTodayLogin(int userId);

    int resetNumWordsMaxReviewAndNumWordsMaxReview(int userId);

    int updateUserScore(int userId, int score, String content);


    List<Integer> getMaxNewWordsNumByUserId(int userId);

    List<Integer> getMaxReviewedWordsNumByUserId(int userId);
}
