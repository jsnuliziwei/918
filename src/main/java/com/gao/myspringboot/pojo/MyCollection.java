package com.gao.myspringboot.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@TableName("mycollection")
@AllArgsConstructor
@NoArgsConstructor
public class MyCollection{
    @MppMultiId
    int userId;
    @MppMultiId
    int wordId;
}
