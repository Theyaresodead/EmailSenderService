package com.example.emailsenderservice.implementation;

import com.example.emailsenderservice.service.EmailService;
import jakarta.mail.internet.MimeMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
public class EmailServiceImp implements EmailService {

    @Autowired
     private JavaMailSender javaMailSender;

    private Logger logger = LoggerFactory.getLogger(EmailServiceImp.class);

    @Override
    public void sendEmail(String to, String subject, String message) {
      SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
      simpleMailMessage.setTo(to);
      simpleMailMessage.setSubject(subject);
      simpleMailMessage.setText(message);
      simpleMailMessage.setFrom("utkarshtewari1234@gmail.com");
      javaMailSender.send(simpleMailMessage);
      logger.info("Email sent");
    }

    @Override
    public void sendEmailToMultipleRecipients(String[] to, String subject, String message) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setFrom("utkarshtewari1234@gmail.com");
        javaMailSender.send(simpleMailMessage);
    }

    @Override
    public void sendEmailWithHtml(String to, String subject, String html)  {
      MimeMessage mimeMessage = javaMailSender.createMimeMessage();
      try {
          MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
          mimeMessageHelper.setTo(to);
          mimeMessageHelper.setSubject(subject);
          mimeMessageHelper.setText(html, true);
          javaMailSender.send(mimeMessage);
          logger.info("Email sent..");
      }catch (Exception e){
          throw new RuntimeException(e);
      }

    }

    @Override
    public void sendEmailWithFile(String to, String subject, String message, InputStream inputStream) {
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        try {
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true,"UTF-8");
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setText(message,true);
            mimeMessageHelper.setSubject(subject);
            File file =new File("src/main/resources/email/urban.png");
            Files.copy(inputStream,file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            FileSystemResource fileSystemResource = new FileSystemResource(file);
            mimeMessageHelper.addAttachment(fileSystemResource.getFilename(),fileSystemResource);
            javaMailSender.send(mimeMessage);
            logger.info("Email sent.");
        }catch (Exception e){
            throw new RuntimeException(e);
        }

    }
}
