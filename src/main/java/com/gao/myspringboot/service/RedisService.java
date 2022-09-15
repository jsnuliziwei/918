package com.gao.myspringboot.service;

import com.gao.myspringboot.pojo.Word;

/**
 * redis操作Service,
 * 对象和数组都以json形式进行存储
 */
public interface RedisService {
    /**
     * 存储数据
     */
    void setWord(int wordId, Word value);


    /**
     * 获取数据
     */
    Word getWord(int wordId);

    /**
     * 设置超期时间
     */
    boolean expire(int wordId, long expire);

    /**
     * 删除数据
     */

    /**
     * 自增操作
     * @param delta 自增步长
     */

}
