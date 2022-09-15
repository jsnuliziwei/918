package com.gao.myspringboot.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gao.myspringboot.mapper.ListenMapper;
import com.gao.myspringboot.pojo.Listen;
import com.gao.myspringboot.service.ListenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListenServiceImpl extends ServiceImpl<ListenMapper,Listen> implements ListenService {
    @Autowired
    ListenMapper listenMapper;

}
