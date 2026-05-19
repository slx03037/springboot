package com.tools.mail.basic.web.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * @author shenlx
 * @description
 * @date 2026/5/19 10:22
 */
@Service
@Slf4j
public class MailService {

    @Autowired
    private JavaMailSender jms;

    @Value("${spring.mail.username}")
    private String from;

    @Autowired
    private TemplateEngine templateEngine;

    /**
     *
     * @param to 接收者 xxxx@foxmail.com
     * @param subject 邮件主题
     * @param content 邮件内容
     * @return
     */
    public void sendSimpleTextMail(String to, String subject, String content) {
        to = "xxxx@foxmail.com";
        subject = "Springboot 发送 HTML 邮件";
        content = "Hi~第一封 Springboot HTML 邮件";
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(content);
            message.setFrom(from); //主机主题邮箱
            jms.send(message);
            log.info("【文本邮件】成功发送！to={}", to);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void sendSimpleEmail() {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom(from);
            message.setTo("xxxx@foxmail.com"); // 接收地址
            message.setSubject("一封简单的邮件"); // 标题
            message.setText("使用Spring Boot发送简单邮件。"); // 内容
            jms.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendHtmlEmail() {
        MimeMessage message = null;
        try {
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo("xxxx@foxmail.com"); // 接收地址
            helper.setSubject("一封HTML格式的邮件"); // 标题
            // 带HTML格式的内容
            helper.setText("<p style='color:#42b983'>使用Spring Boot发送HTML格式邮件。</p>", true);
            jms.send(message);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }

    public void sendAttachmentsMail() {
        MimeMessage message = null;
        try {
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo("xxxx@foxmail.com"); // 接收地址
            helper.setSubject("一封带附件的邮件"); // 标题
            helper.setText("详情参见附件内容！"); // 内容
            // 传入附件
            //FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/file/项目文档.docx"));
            FileSystemResource file = new FileSystemResource(new File("D:\\workspace-github-project\\my-spring-all-com.xinwen.mybatis.node01.service\\spring-boot-email\\src\\main\\resources\\static\\file\\项目文档.docx"));
            helper.addAttachment("项目文档.docx", file);
            jms.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendInlineMail() {
        MimeMessage message = null;
        try {
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo("xxxx@foxmail.com"); // 接收地址
            helper.setSubject("一封带静态资源的邮件"); // 标题
            helper.setText("<html><body>博客图：<img src='cid:img'/></body></html>", true); // 内容
            // 传入附件
            //FileSystemResource file = new FileSystemResource(new File("src/main/resources/static/img/sunshine.png"));
            FileSystemResource file = new FileSystemResource(new File("D:\\workspace-github-project\\my-spring-all-com.xinwen.mybatis.node01.service\\spring-boot-email\\src\\main\\resources\\static\\img\\sunshine.png"));
            helper.addInline("img", file);
            jms.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sendTemplateEmail() {
        MimeMessage message = null;
        try {
            message = jms.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo("xxxx@foxmail.com"); // 接收地址
            helper.setSubject("邮件摸板测试"); // 标题
            // 处理邮件模板
            Context context = new Context();
            context.setVariable("code", "123456");
            context.setVariable("user", "张三");
            String template = templateEngine.process("emailTemplate", context);
            helper.setText(template, true);
            jms.send(message);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
