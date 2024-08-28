package com.example.emailsenderservice.emailsendertest;

import com.example.emailsenderservice.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;


@SpringBootTest
public class EmailSenderTest {

    @Autowired
    private EmailService emailService;
    @Test
    void sendEmailTest() {
        System.out.println("Sending email");
        emailService.sendEmail("utkarshtewari678@gmail.com","From springboot","testing");
    }

    @Test
    void sendHtmlEmailTest() {
        String html=""+
                "<h1>Sending Html </h1>"+"";
        emailService.sendEmailWithHtml("utkarshtewari678@gmail.com","From springboot",html);
    }

    @Test
    void sendFileWithAttachmentsTest() {
        File file =new File("/Users/HP/IdeaProjects/Email Sender Service/src/main/resources/static/urban mali.jpg");
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            emailService.sendEmailWithFile("utkarshtewari678@gmail.com",
                    "Contains File","From Springboot",inputStream );
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
