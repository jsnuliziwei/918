package com.gao.myspringboot.service.Impl;

import com.gao.myspringboot.mapper.CheckMapper;
import com.gao.myspringboot.pojo.Check;
import com.gao.myspringboot.service.CheckService;
import com.gao.myspringboot.service.UserService;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Service
public class CheckServiceImpl implements CheckService {
    @Autowired
    CheckMapper checkMapper;

    @Autowired
    UserService userService;

    @Override
    public int addCheck(int userId) {

        Timestamp now = new Timestamp(new Date().getTime());
        return checkMapper.addCheck(new Check(userId, now));
    }

    @Override
    public boolean isCheckedToday(int userId) {
        Check check = checkMapper.queryLastCheckByUserId(userId);
        if (check != null && DateUtils.isSameDay(new Date(check.getDate().getTime()), new Date())) return true;
        return false;
    }

    @Override
    public List<Check> queryCheckByUserId(Integer userId) {
        return checkMapper.queryCheckByUserId(userId);
    }
}
