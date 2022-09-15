package com.gao.myspringboot.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gao.myspringboot.pojo.UserWord;
import com.gao.myspringboot.pojo.Word;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;


@Mapper
@Repository
public interface UserWordMapper extends BaseMapper<UserWord> {

@Insert("insert into studywords.userword (userId,wordId,study,remember) " +
        "values ( #{userId}, #{wordId}, #{study}, #{remember})")
    int addUserWord(UserWord userWord);

    @Select("select * from studywords.word , studywords.userword " +
            "WHERE word.wordId = userword.wordId and userword.userId = #{userId}")
    List<Word> getOneUserAllWord(int userId);

    @Select("select count(*) from studywords.word , studywords.userword " +
            "WHERE word.wordId = userword.wordId and userword.userId = #{userId} and word.grade = #{grade}")
    int getOneUserAllWordNumber(int userId, int grade);

    @Select("select * from studywords.word WHERE word.wordId not in (select wordId from studywords.userword " +
            "where userId= #{userId}) and grade = #{grade} limit 1")
    Word getOneNotStudyedWord(int userId, int grade);

    @Select("SELECT * FROM studywords.word, studywords.userword where word.wordId = userword.wordId and userId= #{userId} " +
            "and grade = #{grade}  and userword.remember >= #{remembered}")
    List<Word> getOneUserAllRememberedWordByGrade(int userId, int grade, int remembered);

    @Select("SELECT * FROM studywords.word, studywords.userword where word.wordId = userword.wordId " +
            "and userId = #{userId} and grade = #{grade} ORDER BY userword.remember LIMIT 1")
    Word getOneTodayUnreviewWord(int userId, int grade);

    @Delete("DELETE a from userword a,word where a.wordId = word.wordId AND a.userId = #{userId} and word.grade = #{grade}")
    void removeOneUserAllUserWords(int userId, int grade);

}
