package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@SpringBootTest
class DemoApplicationTests {



		// 用于发送邮件的对象
		@Autowired
		private JavaMailSender javaMailSender;

		/**
		 * 发送邮件
		 * @param code 验证码
		 * @param from 发送验证码的邮箱
		 * @param to 接收验证码的邮箱
		 * @return true 发送成功，否则发送失败
		 */
		public boolean sendEmailCode(String code, String from, String to) {
			try {
				// 用来设置邮件信息
				SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
				// 设置邮件标题
				simpleMailMessage.setSubject("宝");
				// 设置邮件内容
				simpleMailMessage.setText("看我头像");
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

		@Test
		public void test1() {
			// 设置验证码，可以自己随机生成
			String code = "011635";
			boolean b = sendEmailCode(code, "cyjshw@163.com", "3152525736@qq.com");
		}



}
