package com.gao.myspringboot.mapper;

import com.gao.myspringboot.pojo.Check;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CheckMapper {
    @Insert("insert into studywords.check(userId, date) values (#{userId},#{date})")
    int addCheck(Check check);

    @Select("select * from studywords.check where userId = #{userId}")
    List<Check> queryCheckByUserId(Integer userId);

    @Select("SELECT * FROM `check` WHERE userId = #{userId} ORDER BY date desc limit 1")
    Check queryLastCheckByUserId(Integer userId);
}
