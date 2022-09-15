package com.gao.myspringboot.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import com.gao.myspringboot.pojo.MyCollection;
import com.gao.myspringboot.pojo.Word;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MyCollectionService extends IService<MyCollection> {
    boolean isCollection(int userId, int wordId);
    int changeCollection(int userId, int wordId);
    Page<Word> getCollectionWordsByPage(int userId, int pageNum, int pageSize);
}
