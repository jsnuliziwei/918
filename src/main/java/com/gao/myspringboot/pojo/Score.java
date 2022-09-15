package com.gao.myspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Score {
    @TableId(value = "id",type = IdType.AUTO)
    int id;
    int userId;
    int score;
    int scoreSum;
    String content;
    private Timestamp date;
}
