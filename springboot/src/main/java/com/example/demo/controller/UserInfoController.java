package com.example.demo.controller;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.UserInfoMapper;
import org.springframework.web.bind.annotation.*;
import com.example.demo.common.Result;
import com.example.demo.Utils.Predict;
import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/user")
public class UserInfoController {

    @Resource
    UserInfoMapper userInfoMapper;

    @RequestMapping("/login")
    public Result<?> query(@RequestBody UserInfo userInfo){
        LambdaQueryWrapper<UserInfo> wrapper = Wrappers.<UserInfo>lambdaQuery();
        wrapper.eq(UserInfo::getUsername,userInfo.getUsername()).eq(UserInfo::getPassword,userInfo.getPassword());

        System.out.println(userInfo);
//        userInfoMapper.insert(userInfo);
        UserInfo userInfo1 = userInfoMapper.selectOne(wrapper);
        System.out.println(userInfo1);
        if (userInfo1 == null){
            return Result.error("1","用户名或密码错误");

        }else {
            return Result.success(userInfo);
        }
    }

    @RequestMapping("/register")
    public Result<?> save(@RequestBody UserInfo userInfo){


        return Result.success();
    }
    @RequestMapping("/hello")
    public String hello() throws IOException {
        System.out.println("hello");
        double[] pre = {0.01,1,1,1,1,1,1,1,1,1};
        String a = Predict.predict(0,pre);
        System.out.println(a);
        return a;
    }
}
