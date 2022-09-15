package com.gao.myspringboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.gao.myspringboot.pojo.UserWord;
import com.gao.myspringboot.pojo.Word;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserWordService extends IService<UserWord> {
    public List<Word> getOneUserAllRememberedByGrade(int userId, int remember, int grade);

    /**
     * 收藏部分,迁移至收藏表
     */

    public int clearRemember(int userId, int wordId);

    public List<Word> getOneUserAllWord(int userId);


    public Word getOneNotStudyedWord(int userId, int grade);

    Word getOneTodayUnreviewWord(int userId, int grade);

    public boolean isUserWord(int userId, int wordId);

    int testAndSetUserWord(int userId, int wordId);

    public int addUserWord(int userId, int wordId);

    public int addRemember(int userId, int wordId);

    public int getOneUserAllWordNumber(int userId, int grade);

    int minusOneRemember(int userId, int wordId);

    public int getOneUserRememberedWordNumberByGrade(int userId, int grade);

    int removeOneUserAllUserWords(int userId, int grade);
}
