package com.zz.helloWorld.hello;

import javax.annotation.Resource;
import javax.mail.MessagingException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.sun.mail.handlers.text_html;
import com.zz.helloWorld.hello.MailService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TestMailService {

	@Resource
	MailService mailService;
	
	@Resource
	TemplateEngine template;
	
	@Test
	public void testMailService(){
		mailService.sayHello();
	}
	//普通邮件
	@Test
	public void testMailSender(){
		mailService.sendSimpleMail("Zzj140711@163.com", "你好我是张子健", "测试我的第一封邮件你觉得我帅吗");
	}
	//发送普通的html邮件
	@Test
	public void testHtmlMail() throws MessagingException{
		String content = "<html>\n" + "<body>\n" + "<h2>我是张子健</h2>\n" + "</body>\n" + "</html>";
		
		mailService.sendHtmlMail("1241646494@qq.com", "你好我是张子健", content);
	}
	//发送附件
	@Test
	public void testSendAttachMail() throws MessagingException{
		String filepath = "G://lushhi.txt";
		mailService.sendAttachMail("1241646494@qq.com", "你好我是张子健", "这是我的文件", filepath);
	}
	//发送带图片的html邮件
	@Test
	public void testSendInlinResourceMail() throws MessagingException{
		
		String imgPath = "D://spanman.jpg";
		String rscId = "ironman";
		String content = "<html><body>这是钢铁侠<img src=\'cid:" + rscId + "\'></body></html>";
		
		mailService.sendInlinResourceMail("1241646494@qq.com", "钢铁侠图片", content, imgPath, rscId);
	}
	
	//发送模板邮件
	@Test
	public void testTemplateMail() throws MessagingException{
		Context context = new Context();
		context.setVariable("user", "luxin-fan");
		
		String emailContent = template.process("emailTemplate", context);
		mailService.sendHtmlMail("1241646494@qq.com", "这是一个模板邮件", emailContent);
	}
}
