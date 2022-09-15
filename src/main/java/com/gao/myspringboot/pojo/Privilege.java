package com.gao.myspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Privilege {
    @TableId(value = "id",type = IdType.AUTO)
    int id;
    int newWordsNum;
    int reviewedWordsNum;
    int score;
}
