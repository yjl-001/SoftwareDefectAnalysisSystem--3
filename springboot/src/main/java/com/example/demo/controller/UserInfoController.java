package com.example.demo.controller;

import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.demo.entity.UserInfo;
import com.example.demo.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.object.UpdatableSqlQuery;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
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

    @Autowired
    private JavaMailSender javaMailSender;


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
            return Result.success(userInfo1);
        }
    }

    @RequestMapping("/sendemail")
    public Result<?> send(@RequestBody UserInfo userInfo){
        String code = "081212";
        String from = "cyjshw@163.com";
        String to = userInfo.getEmail();
        boolean b = sendEmailCode(code,from,to);
        if (b){
            return Result.success();
        }else {
            return Result.error("0","错了");
        }
    }
    @RequestMapping("/register")
    public Result<?> save(@RequestBody UserInfo userInfo){
        if (userInfo.getVerifying().equals("081212")){
            int i1 = userInfoMapper.insert(userInfo);
            if (i1==1){
                return Result.success();
            }else {
                return Result.error("0","添加失败");
            }
        }else {
            return Result.error("0","验证码错误");
        }

    }

    @RequestMapping("/alterEmail")
    public Result<?> alterE(@RequestParam int userid,
                            @RequestParam String oldEmail,
                            @RequestParam String newEmail){
        LambdaQueryWrapper<UserInfo> wrapper = Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getUserid,userid);
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);
        if (userInfo.getEmail().equals(oldEmail)){
            userInfo.setEmail(newEmail);
            UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("userid",userInfo.getUserid());
            userInfoMapper.update(userInfo,updateWrapper);
            return Result.success();
        }else {
            return Result.error("0","邮箱错误");
        }
    }

    @RequestMapping("/alterPassword")
    public Result<?> alterP(@RequestParam(defaultValue = "3") int userid,
                            @RequestParam(defaultValue = "sda") String oldPassword,
                            @RequestParam(defaultValue = "gaile") String newPassword){
        LambdaQueryWrapper<UserInfo> wrapper = Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getUserid,userid);
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);
        if (userInfo.getPassword().equals(oldPassword)){
            userInfo.setPassword(newPassword);
            UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
            updateWrapper.eq("userid",userInfo.getUserid());
            userInfoMapper.update(userInfo,updateWrapper);
            return Result.success();
        }else {
            return Result.error("0","密码错误");
        }
    }

    @RequestMapping("/alterInfo")
    public Result<?> alterI(@RequestParam(defaultValue = "") int userid,
                            @RequestParam(defaultValue = "") String name,
                            @RequestParam(defaultValue = "") String number,
                            @RequestParam(defaultValue = "") String sex,
                            @RequestParam(defaultValue = "") String job){
        if (StrUtil.isNotBlank(name) || StrUtil.isNotBlank(number) || StrUtil.isNotBlank(sex) || StrUtil.isNotBlank(job)){

        }else {
            return Result.error("0","啥也没填改啥呢！");
        }
        LambdaQueryWrapper<UserInfo> wrapper = Wrappers.<UserInfo>lambdaQuery().eq(UserInfo::getUserid,userid);
        UserInfo userInfo = userInfoMapper.selectOne(wrapper);
        if (StrUtil.isNotBlank(name)){
            userInfo.setUsername(name);
        }
        if (StrUtil.isNotBlank(number)){
            userInfo.setPhone(number);
        }
        if (StrUtil.isNotBlank(sex)){
            if (sex == "man" || sex=="woman"){
                userInfo.setSex(sex);
            }else {
                return Result.error("0","性别为man或woman");
            }
        }
        if (StrUtil.isNotBlank(job)){
            userInfo.setJob(job);
        }
        UpdateWrapper<UserInfo> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("userid",userInfo.getUserid());
        userInfoMapper.update(userInfo,updateWrapper);
        return Result.success();

    }

    @RequestMapping("/hello")
    public String hello() throws IOException {
        System.out.println("hello");
        double[] pre = {0.01,1,1,1,1,1,1,1,1,1};
        String a = Predict.predict(0,1,pre);
        System.out.println(a);
        return a;
    }


    public boolean sendEmailCode(String code, String from, String to) {
        try {
            // 用来设置邮件信息
            SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
            // 设置邮件标题
            simpleMailMessage.setSubject("你好，你的登录验证码");
            // 设置邮件内容
            simpleMailMessage.setText("您收到的验证码是：" + code+"收到请回复");
            // 设置源邮箱
            simpleMailMessage.setFrom(from);
            // 设置目的邮箱
            simpleMailMessage.setTo(to);
            // 发送
            javaMailSender.send(simpleMailMessage);
            // 没有出现异常，正常发送，返回true
            return true;
        } catch (MailException e) {
            // 发送过程中，发生错误，打印错误信息，返回false
            e.printStackTrace();
            return false;
        }
    }
}
