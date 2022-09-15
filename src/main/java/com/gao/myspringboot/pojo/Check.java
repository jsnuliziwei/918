package com.gao.myspringboot.pojo;

import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Check {
    @MppMultiId
    private Integer userId;
    @MppMultiId
    private Timestamp date;
}
