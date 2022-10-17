package com.example.demo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("userinfo")
@Data
public class UserInfo {
    private int userid;
    private String username;
    private String password;
    private String email;
    private String job;
    private Date birthday;
    private String phone;
}
