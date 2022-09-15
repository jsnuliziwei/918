package com.gao.myspringboot.service;

import com.gao.myspringboot.pojo.Check;

import java.util.List;

public interface CheckService {
    int addCheck(int userId);

    List<Check> queryCheckByUserId(Integer userId);

    boolean isCheckedToday(int userId);
}
