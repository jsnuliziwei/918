package com.gao.myspringboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gao.myspringboot.mapper.UserWordMapper;
import com.gao.myspringboot.pojo.UserWord;
import com.gao.myspringboot.pojo.Word;
import com.gao.myspringboot.service.UserWordService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserWordServiceImpl extends ServiceImpl<UserWordMapper, UserWord> implements UserWordService {
    @Autowired
    UserWordMapper userWordMapper;

    @Override
    public List<Word> getOneUserAllRememberedByGrade(int userId, int grade, int remember) {
        return userWordMapper.getOneUserAllRememberedWordByGrade(userId, grade, remember);
    }

    private int addUserWordRemember(int userId, int wordId) {
        QueryWrapper<UserWord> queryWrapper = new QueryWrapper<UserWord>();
        queryWrapper.lambda()
                .eq(UserWord::getUserId, userId)
                .eq(UserWord::getWordId, wordId);
        UserWord userWord = userWordMapper.selectOne(queryWrapper);
        userWord.addRemember();
        userWordMapper.update(userWord, queryWrapper);
        return 0;
    }

    @Override
    public List<Word> getOneUserAllWord(int userId) {
        return userWordMapper.getOneUserAllWord(userId);
    }

    @Override
    public int clearRemember(int userId, int wordId) {
        QueryWrapper<UserWord> queryWrapper = new QueryWrapper<UserWord>();
        queryWrapper.lambda()
                .eq(UserWord::getUserId, userId)
                .eq(UserWord::getWordId, wordId);
        UserWord userWord = userWordMapper.selectOne(queryWrapper);
        userWord.setRemember(0);
        userWordMapper.update(userWord, queryWrapper);
        return 0;
    }

    @Override
    public int getOneUserAllWordNumber(int userId, int grade) {
        return userWordMapper.getOneUserAllWordNumber(userId, grade);
    }

    @Override
    public Word getOneNotStudyedWord(int userId, int grade) {
        return userWordMapper.getOneNotStudyedWord(userId, grade);
    }

    @Override
    public Word getOneTodayUnreviewWord(int userId, int grade) {
        return userWordMapper.getOneTodayUnreviewWord(userId, grade);
    }

    /**
     * 是否在UserWord表中，表示这个单词该用户是否学过
     *
     * @param userId
     * @param wordId
     * @return
     */
    @Override
    public boolean isUserWord(int userId, int wordId) {
        QueryWrapper<UserWord> queryWrapper = new QueryWrapper<UserWord>();
        queryWrapper.lambda()
                .eq(UserWord::getUserId, userId)
                .eq(UserWord::getWordId, wordId);
        UserWord userWord = userWordMapper.selectOne(queryWrapper);
        return userWord != null;
    }

    /**
     * 判断是否已经学习过该单词
     * 没学过则加入, 学过则不变
     */
    @Override
    public int testAndSetUserWord(int userId, int wordId) {
        if (isUserWord(userId, wordId)) return 1;
        else return addUserWord(userId, wordId);
    }

    @Override
    public int addUserWord(int userId, int wordId) {
        UserWord userWord = new UserWord();
        userWord.setUserId(userId);
        userWord.setWordId(wordId);
        userWord.setStudy(1);
        userWord.setRemember(0);
        userWordMapper.addUserWord(userWord);
        return 0;
    }

    @Override
    public int addRemember(int userId, int wordId) {
        if (!isUserWord(userId, wordId)) addUserWord(userId, wordId);
        addUserWordRemember(userId, wordId);
        return 0;
    }

    @Override
    public int minusOneRemember(int userId, int wordId) {
        if (!isUserWord(userId, wordId)) addUserWord(userId, wordId);
        QueryWrapper<UserWord> queryWrapper = new QueryWrapper<UserWord>();
        queryWrapper.lambda()
                .eq(UserWord::getUserId, userId)
                .eq(UserWord::getWordId, wordId);
        UserWord userWord = userWordMapper.selectOne(queryWrapper);
        userWord.minusOneRemember();
        userWordMapper.update(userWord, queryWrapper);
        return 0;
    }

    @Override
    public int getOneUserRememberedWordNumberByGrade(int userId, int grade) {
        int remember = 0;
        return getOneUserAllRememberedByGrade(userId, grade, remember).size();
    }

    @Override
    public int removeOneUserAllUserWords(int userId, int grade) {
        userWordMapper.removeOneUserAllUserWords(userId, grade);
        return 0;
    }
}
