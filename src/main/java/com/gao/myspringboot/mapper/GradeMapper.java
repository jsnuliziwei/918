package com.gao.myspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gao.myspringboot.pojo.Grade;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface GradeMapper extends BaseMapper<Grade> {

}
