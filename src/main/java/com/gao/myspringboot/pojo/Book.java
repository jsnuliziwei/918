package com.gao.myspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

@Data
public class Book {
    @TableId(value = "bookId",type = IdType.AUTO)
    private int bookId;
    private String bookName;
    private String bookUser;
    private String description;
    private String content;
}
