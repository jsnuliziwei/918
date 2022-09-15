package com.gao.myspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Listen {
    @TableId(value = "listenId",type = IdType.AUTO)
    private Integer listenId;
    private String listenName;
    private Integer grade;
    private String path;
    private String content;
}
