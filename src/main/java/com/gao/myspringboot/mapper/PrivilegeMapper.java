package com.gao.myspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gao.myspringboot.pojo.Privilege;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PrivilegeMapper extends BaseMapper<Privilege> {
    @Select("SELECT Max(newWordsNum) FROM `privilege` where score <= #{score}")
    int getPermissionMaxNewWordsNum(int score);

    @Select("SELECT Max(reviewedWordsNum) FROM `privilege` where score <= #{score}")
    int getPermissionMaxReviewedWordsNum(int score);

    @Select("select * from privilege where score <= #{score} order by score")
    List<Privilege> getPrivilegesByScore(int score);
}
