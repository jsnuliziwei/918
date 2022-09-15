package com.gao.myspringboot.util;

import com.gao.myspringboot.pojo.Word;
import com.gao.myspringboot.service.RedisService;
import com.gao.myspringboot.service.WordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;

@Controller
public class WordRedisUtils {
    @Value("${redis.key.expire.word}")
    private long expireTime;
    @Autowired
    private RedisService redisService;
    @Autowired
    private WordService wordService;
    public void setWordCache(Word word) {
        redisService.setWord(word.getWordId(), word);
        redisService.expire(word.getWordId(), expireTime);
    }
    public Word getWordCache(int wordId) {
        Word word = redisService.getWord(wordId);
        return word==null?wordService.queryWordById(wordId):word;
    }
}
