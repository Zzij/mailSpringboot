package com.zz.helloWorld.hello;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.zz.helloWorld.hello.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMailService {

	@Resource
	MailService mailService;
	
	@Test
	public void testMailService(){
		mailService.sayHello();
	}
	
	@Test
	public void testMailSender(){
		mailService.sendSimpleMail("Zzj140711@163.com", "你好我是张子健", "测试我的第一封邮件你觉得我帅吗");
	}
	
	@Test
	public void testHtmlMail() throws MessagingException{
		String content = "<html>\n" + "<body>\n" + "<h2>我是张子健</h2>\n" + "</body>\n" + "</html>";
		
		mailService.sendHtmlMail("1241646494@qq.com", "你好我是张子健", content);
	}
	
	@Test
	public void testSendAttachMail() throws MessagingException{
		String filepath = "G://lushhi.txt";
		mailService.sendAttachMail("1241646494@qq.com", "你好我是张子健", "这是我的文件", filepath);
	}
	
	@Test
	public void testSendInlinResourceMail() throws MessagingException{
		
		String imgPath = "G://spanman.jpg";
		String rscId = "ironman";
		String content = "<html><body>这是钢铁侠<img src=\'cid:" + rscId + "\'></body></html>";
		
		mailService.sendInlinResourceMail("1241646494@qq.com", "钢铁侠图片", content, imgPath, rscId);
	}
}
