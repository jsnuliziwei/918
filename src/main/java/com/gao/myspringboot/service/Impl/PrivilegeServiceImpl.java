package com.gao.myspringboot.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.gao.myspringboot.mapper.PrivilegeMapper;
import com.gao.myspringboot.pojo.Privilege;
import com.gao.myspringboot.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PrivilegeServiceImpl extends ServiceImpl<PrivilegeMapper, Privilege> implements PrivilegeService {
    @Autowired
    private PrivilegeMapper privilegeMapper;

    @Override
    public List<Integer> getMaxNewWordsNumByScore(int score) {
        List<Privilege> privilegesByScore = privilegeMapper.getPrivilegesByScore(score);
        List<Integer> list = new ArrayList<>();
        for (Privilege privilege : privilegesByScore) {
            list.add(privilege.getNewWordsNum());
        }
        return list;
    }

    @Override
    public List<Integer> getMaxReviewedWordsNumByScore(int score) {
        List<Privilege> privilegesByScore = privilegeMapper.getPrivilegesByScore(score);
        List<Integer> list = new ArrayList<>();
        for (Privilege privilege : privilegesByScore) {
            list.add(privilege.getReviewedWordsNum());
        }
        return list;
    }
}
