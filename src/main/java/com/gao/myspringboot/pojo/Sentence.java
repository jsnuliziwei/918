package com.gao.myspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Sentence {
    @TableId(value = "sentenceId",type = IdType.AUTO)
    private Integer sentenceId;
    private String sentenceName;
    private String explain;
}
