package com.gao.myspringboot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;
/*
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
*/

import java.sql.Timestamp;
import java.util.Arrays;
import java.util.Collection;

@Data
@NoArgsConstructor
@Schema(description = "用户实体类")
public class User //implements UserDetails
{
    @TableId(value = "userId",type = IdType.AUTO)
    private Integer userId;
    private String userName; //这里应该是username
    private String password;
    private String email;

    private int role;

    private Timestamp lastLoginTime;

    private int numNewWordsMaxDay = 5;
    private int numNewWordsToday;

    public void addOneNumWordsToday() {
        this.numNewWordsToday++;
    }

    public void resetNumWordsToday() {
        this.numNewWordsToday = 0;
    }

    int numWordsMaxReview = 5;
    int numWordsReviewToday;

    public void addOneNumWordsReviewToday() {
        this.numWordsReviewToday++;
    }

    public void resetNumWordsReviewToday() {
        this.numWordsReviewToday = 0;
    }

    public boolean isNewFinishedToday() {
        return this.numNewWordsToday >= this.numNewWordsMaxDay;
    }

    public boolean isReviewFinishedToday() {
        return this.numWordsReviewToday >= this.numWordsMaxReview;
    }

    int score;
}

