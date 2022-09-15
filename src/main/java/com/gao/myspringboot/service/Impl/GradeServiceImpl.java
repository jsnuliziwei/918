package com.gao.myspringboot.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gao.myspringboot.mapper.GradeMapper;
import com.gao.myspringboot.pojo.Grade;
import com.gao.myspringboot.service.GradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GradeServiceImpl extends ServiceImpl<GradeMapper,Grade> implements GradeService {
    @Autowired
    GradeMapper gradeMapper;

}
