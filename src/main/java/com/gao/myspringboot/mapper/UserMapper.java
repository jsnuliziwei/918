package com.gao.myspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gao.myspringboot.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {


    /**
     * 注册用户
     * 与登录用户
     */
    @Select("select * from user where email=#{email} and password=#{password}")
    User loginByEmailAndPassword(User user);
    //根据账号密码和role=1登录
    @Select("select * from user where studywords.user.email=#{email} and password=#{password} and role=1")
    User AdminLogin(User user);

    /**
     * 操作用户
     */

    //删除用户
    int deleteUser(Integer userId);

    //修改用户
    @Update("update studywords.user set userName = #{userName}, email = #{email},password=#{password}," +
            "numNewWordsMaxDay= #{numNewWordsMaxDay}, numWordsMaxReview = #{numWordsMaxReview}   where userId=#{userId} ;")
    int updateUser(User user);

    //根据Id查询用户
    User queryUserById(int userId);

    @Update("UPDATE user set score=#{score} where userId = #{userId};")
    int updateUserScore(int userId, int score);


}
