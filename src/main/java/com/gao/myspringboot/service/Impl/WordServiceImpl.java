package com.gao.myspringboot.service.Impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.gao.myspringboot.mapper.WordMapper;
import com.gao.myspringboot.pojo.Word;
import com.gao.myspringboot.service.WordService;
//import com.github.pagehelper.PageHelper;
//import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WordServiceImpl implements WordService {
    @Autowired
    private WordMapper wordMapper;

    @Override
    public int addWord(Word word) {
        return wordMapper.addWord(word);
    }

    @Override
    public int deleteWord(Integer wordId) {
        return wordMapper.deleteWord(wordId);
    }

    @Override
    public int updateWord(Word word) {
        return wordMapper.updateWord(word);
    }

    @Override
    public Word queryWordById(Integer wordId) {
        return wordMapper.queryWordById(wordId);
    }

    @Override
    public int queryAllWordNumberByGrade(Integer grade) {
        return wordMapper.queryAllWordNumberByGrade(grade);
    }

    @Override
    public Word queryWordByWord(String word) {
        List<Word> words = wordMapper.queryWordByWord(word);
        if (words.size() > 0) return words.get(0);
        else return null;
    }

    @Override
    public Page<Word> selectWordByPage(int pageNum, int pageSize) {
        return wordMapper.selectPageWordByPage(new Page<>(pageNum, pageSize));
    }
    @Override
    public List<Word> queryWordByExplanation(String explanation){
        return wordMapper.queryWordByExplanation(explanation);
    }
}
