package com.gao.myspringboot.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.gao.myspringboot.pojo.Listen;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ListenMapper  extends BaseMapper<Listen> {
    //根据ID查询一条听力的信息
    Listen queryListenById(Integer listenId);

    //删除一条听力
    int deleteListen(Integer listenId);

    //修改一条听力
    int updateListen(Listen listen);

}
