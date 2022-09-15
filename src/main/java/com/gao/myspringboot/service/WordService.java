package com.gao.myspringboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gao.myspringboot.pojo.Word;
//import com.github.pagehelper.PageInfo;

import java.util.List;

public interface WordService {


    /**
     * ############################
     * 增加
     * #############################
     */
    //增加一个单词
    int addWord(Word word);


    /**
     * #############################
     * 删除
     * #############################
     */
    //删除一个单词
    int deleteWord(Integer wordId);

    /**
     * #############################
     * 修改
     * #############################
     */
    //修改一个单词
    int updateWord(Word word);

/**
     * #############################
     * 查询
     * #############################
     */

    //根据ID查询一个单词的信息
    Word queryWordById(Integer wordId);

    /*
      ---------------------------
            查询数量功能
      ---------------------------
     */
    //根据grade查询单词数量
    int queryAllWordNumberByGrade(Integer grade);

    /*
       ---------------------------
            搜索功能
       ---------------------------
     */
    // 根据单词查询单词
    Word queryWordByWord(String word);

    Page<Word> selectWordByPage(int pageNum, int pageSize);

    List<Word> queryWordByExplanation(String explanation);
}
