package com.zz.helloWorld.hello;

import java.io.File;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class MailService {
	@Value("${spring.mail.username}")
	private String from;
	
	@Autowired
	private JavaMailSender mailSender;
	
	
	public void sayHello(){
		System.out.println("hello world");
	}
	//发送普通邮件
	public void sendSimpleMail(String to, String subject, String content){
		
		SimpleMailMessage  message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content);
		message.setFrom(from);
		mailSender.send(message);
		
	}
	//发送html页面
	public void sendHtmlMail(String to, String subject, String content) throws MessagingException{
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(content, true);
		mailSender.send(message);
		
	}
	//发送给有附件的邮件
	public void sendAttachMail(String to, String subject, String content, String filepath) throws MessagingException{
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message,true);
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(content, true);
		FileSystemResource file = new FileSystemResource(new File(filepath));
		String filename = file.getFilename();
		helper.addAttachment(filename, file);
		mailSender.send(message);
		
	}
	
	//发送带图片的邮件
	public void sendInlinResourceMail(String to, String subject, String content, String rscPath, String rscId) throws MessagingException{
		
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
		
		helper.setFrom(from);
		helper.setTo(to);
		helper.setSubject(subject);
		helper.setText(content, true);
		
		FileSystemResource res = new FileSystemResource(new File(rscPath));
		helper.addInline(rscId, res);
		
		mailSender.send(message);
	}

}
