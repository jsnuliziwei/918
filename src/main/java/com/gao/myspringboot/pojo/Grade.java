package com.gao.myspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Grade {
    @TableId(value = "gradeId",type = IdType.AUTO)
    private Integer gradeId;
    private String gradeName;
}
