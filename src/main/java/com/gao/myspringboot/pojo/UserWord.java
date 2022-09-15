package com.gao.myspringboot.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.github.jeffreyning.mybatisplus.anno.MppMultiId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("userword")
public class UserWord {
    @MppMultiId
    private int userId;
    @MppMultiId
    private int wordId;
    private int study;
    private int remember;
    public void addRemember() {
        this.remember++;
    }

    public void minusOneRemember() {
        this.remember--;
    }
}
