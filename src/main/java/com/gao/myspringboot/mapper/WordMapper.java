package com.gao.myspringboot.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gao.myspringboot.pojo.Word;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface WordMapper extends BaseMapper<Word> {


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
    //根据word查询单词
    List<Word> queryWordByWord(String word);

    @Select("SELECT * FROM word")
    List<Word> selectWordByPage(IPage<Word> page);

    @Select("SELECT * FROM word")
    Page<Word> selectPageWordByPage(IPage<Word> page);

    @Select("SELECT * FROM word WHERE MATCH(explanation) AGAINST (#{explanation})")
    List<Word> queryWordByExplanation(String explanation);
}
