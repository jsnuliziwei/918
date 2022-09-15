package com.gao.myspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;

@Data
public class Word implements Serializable {
    @TableId(value = "wordId",type = IdType.AUTO)
    private Integer wordId;
    private String wordName;
    private String audio;
    private String explanation;
    private String example;
    private Integer grade;
}
