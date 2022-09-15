package com.gao.myspringboot.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.gao.myspringboot.pojo.Privilege;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PrivilegeService extends IService<Privilege> {
    List<Integer> getMaxNewWordsNumByScore(int score);

    List<Integer> getMaxReviewedWordsNumByScore(int score);
}
