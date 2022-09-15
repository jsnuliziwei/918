package com.gao.myspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gao.myspringboot.pojo.Score;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ScoreMapper extends BaseMapper<Score> {
    @Select("SELECT * FROM `score` WHERE userId = #{userId} and content = #{content} order by date desc limit 1")
    Score queryLastScoreByContent(int userId, String content);
}
