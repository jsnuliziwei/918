package com.gao.myspringboot.service.Impl;

import com.gao.myspringboot.pojo.Word;
import com.gao.myspringboot.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * redis操作Service的实现类
 */
@Service
public class RedisServiceImpl implements RedisService {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void setWord(int wordId, Word value) {
        redisTemplate.opsForValue().set(wordId,value);
    }

    @Override
    public Word getWord(int wordId) {
        return (Word) redisTemplate.opsForValue().get(wordId);
    }

    @Override
    public boolean expire(int wordId, long expire) {
        return redisTemplate.expire(wordId, expire, TimeUnit.SECONDS);
    }

}
