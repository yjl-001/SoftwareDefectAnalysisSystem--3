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
        userInfo.setUsername("test");
        userInfo.setPassword("12345");
        System.out.println("结果："+usercontroller.query(userInfo).getCode());
//        assertEquals("1",usercontroller.query(userInfo).getCode());//失败
        assertEquals("0",usercontroller.query(userInfo).getCode());//成功
    }

    @Test
    public void send() {
        //发送验证码请求，1验证码错误
        UserInfo userInfo = new UserInfo();
        userInfo.setEmail("2924058897@qq.com");
        String code = usercontroller.send(userInfo).getCode();
        System.out.println("返回结果"+code);
//        assertEquals("1",code);//失败
        assertEquals("0",code);//成功
    }

    @Test
    public void save() {
        UserInfo userInfo = new UserInfo();
        userInfo.setUserid(6);
        userInfo.setUsername("test");
        userInfo.setPassword("12345");
        userInfo.setVerifying("081");
        userInfo.setEmail("123213@qq.com");
        userInfo.setJob("测试");
        userInfo.setSex("man");
        userInfo.setPhone("1233123213");
        String code = usercontroller.save(userInfo).getCode();
        System.out.println("返回结果"+code);
//        assertEquals("1",code);//失败
        assertEquals("0",code);//成功

    }

    @Test
    public void alterE() {
        String code = usercontroller.alterE(5,"13221321@qq.com","2924058897@qq.com").getCode();
        System.out.println("返回结果"+code);
//        assertEquals("1",code);//失败
        assertEquals("0",code);//成功
    }

    @Test
    public void alterP() {
        String code = usercontroller.alterP(5,"123","12345").getCode();
        System.out.println("返回结果"+code);
//        assertEquals("1",code);//失败
        assertEquals("0",code);//成功
    }

    @Test
    public void alterI() {
        String code = usercontroller.alterI(5,"usa","123456","男","测试").getCode();
        System.out.println("返回结果"+code);
//        assertEquals("1",code);//失败
        assertEquals("0",code);//成功
    }

    @Test
    public void sendEmailCode() {
        boolean flag = usercontroller.sendEmailCode("11","cyjshw@163.com","2924058897@qq.com");
        System.out.println("返回结果"+flag);
//        assertTrue(flag);
        assertFalse(flag);
    }
}
