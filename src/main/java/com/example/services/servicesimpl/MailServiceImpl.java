package com.example.services.servicesimpl;

import com.example.services.MailService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;

/**
 * @author: yangjie
 * @data:2021/12/23 11:48
 */
@Service
public class MailServiceImpl implements MailService{
    @Value("${spring.mail.username}")
    private String mailUsername;
    @Resource
    private JavaMailSender javaMailSender;
    @Resource
    private TemplateEngine templateEngine;

    /**
     * 激活邮件发送
     * @param activitionUrl 激活邮箱地址
     * @param email 收件人邮箱
     */
    public void sendMailForActivitionUser(String activitionUrl,String email){
        //创建邮件对象
        MimeMessage mimeMessage=javaMailSender.createMimeMessage();
        try{
            MimeMessageHelper message=new MimeMessageHelper(mimeMessage,true);
            //邮件主题
            message.setSubject("丽丽提醒你 - 该把账号激活了！");
            //设置邮件发送者
            message.setFrom(mailUsername);
            //设置邮件接收者
            message.setTo(email);
            //设置邮件发送日期
            message.setSentDate(new Date());
            //创建上下文环境
            Context context=new Context();
            context.setVariable("activitionUrl",activitionUrl);
            String text=templateEngine.process("activition.html",context);
            //设置邮件正文
            message.setText(text,true);
        }catch (MessagingException e){
            e.printStackTrace();
        }
        javaMailSender.send(mimeMessage);

    }
}
