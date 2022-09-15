package com.gao.myspringboot.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gao.myspringboot.mapper.MyCollectionMapper;
import com.gao.myspringboot.pojo.MyCollection;
import com.gao.myspringboot.pojo.UserWord;
import com.gao.myspringboot.pojo.Word;
import com.gao.myspringboot.service.MyCollectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyCollectionServiceImpl extends ServiceImpl<MyCollectionMapper, MyCollection> implements MyCollectionService {
    @Autowired
    MyCollectionMapper myCollectionMapper;
    @Override
    public boolean isCollection(int userId, int wordId){
        QueryWrapper<MyCollection> queryWrapper = new QueryWrapper<MyCollection>();
        queryWrapper.lambda()
                .eq(MyCollection::getUserId, userId)
                .eq(MyCollection::getWordId, wordId);
        MyCollection myCollection = myCollectionMapper.selectOne(queryWrapper);
        if (myCollection == null ) return false;
        return true;
    }

    @Override
    public int changeCollection(int userId, int wordId) {
        if(isCollection(userId,wordId)){
            QueryWrapper<MyCollection> queryWrapper = new QueryWrapper<MyCollection>();
            queryWrapper.lambda()
                    .eq(MyCollection::getUserId, userId)
                    .eq(MyCollection::getWordId, wordId);
            myCollectionMapper.delete(queryWrapper);
        }else {
            MyCollection myCollection = new MyCollection(userId,wordId);
            myCollectionMapper.insert(myCollection);
        }
        return 0;
    }


    @Override
    public Page<Word> getCollectionWordsByPage(int userId, int pageNum, int pageSize) {
        IPage<Word> page = new Page<Word>(pageNum, pageSize);
        return myCollectionMapper.getCollectionWordsByPage(page,userId);
    }

    ;
}
