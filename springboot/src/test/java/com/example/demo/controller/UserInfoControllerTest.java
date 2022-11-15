package com.example.demo.controller;

import com.example.demo.entity.UserInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class UserInfoControllerTest {
    @Autowired
    private UserInfoController usercontroller;
    @Test
    public void query() {
        //登录请求，分为两种情况，1账户名or密码错误2正常登录
        //1 Result.error("1","用户名或密码错误")
        //2 Result.success(userInfo1)
        UserInfo userInfo = new UserInfo();
        userInfo.setUsername("aa");
        userInfo.setPassword("da");
        assertEquals("1",usercontroller.query(userInfo).getCode());//失败
        assertEquals("0",usercontroller.query(userInfo).getCode());//成功
    }

    @Test
    public void send() {
        //发送验证码请求，1验证码错误
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("123@163.com");
        String code = usercontroller.send(userInfo).getCode();
        System.out.println("返回结果"+code);
        assertEquals("1",code);//失败
        assertEquals("0",code);//成功
    }

    @Test
    public void save() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserid(5);
        userInfo.setUsername("test");
        userInfo.setPassword("12345");
        userInfo.setVerifying("081212");
        userInfo.setEmail("111@qq.com");
        userInfo.setJob("测试");
        userInfo.setSex("女");
        userInfo.setPhone("12313123123");
        String code = usercontroller.save(userInfo).getCode();
        System.out.println("返回结果"+code);
        assertEquals("1",code);//失败
        assertEquals("0",code);//成功

    }

    @Test
    public void alterE() {
        String code = usercontroller.alterE(5,"111@qq.com","13221321@qq.com").getCode();
        System.out.println("返回结果"+code);
        assertEquals("1",code);//失败
        assertEquals("0",code);//成功
    }

    @Test
    public void alterP() {
        String code = usercontroller.alterP(5,"12345","123456").getCode();
        System.out.println("返回结果"+code);
        assertEquals("1",code);//失败
        assertEquals("0",code);//成功
    }

    @Test
    public void alterI() {
        String code = usercontroller.alterI(1,"usa","123456","女","测试").getCode();
        System.out.println("返回结果"+code);
        assertEquals("1",code);//失败
        assertEquals("0",code);//成功
    }

    @Test
    public void sendEmailCode() {
        boolean flag = usercontroller.sendEmailCode("11","usa","123456");
        System.out.println("返回结果"+flag);
        assertTrue(flag);//失败
        assertFalse(flag);//成功
    }
}
