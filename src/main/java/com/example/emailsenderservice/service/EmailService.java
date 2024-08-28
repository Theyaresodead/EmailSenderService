package com.example.emailsenderservice.service;

import java.io.File;
import java.io.InputStream;


public interface EmailService {

    // send email to single person
    void sendEmail(String to, String subject, String message);

    // send email to multiple user
    void sendEmailToMultipleRecipients(String []to, String subject, String message);

    // send email with html content
    void sendEmailWithHtml(String to, String subject, String html);

    // send email with file
    void sendEmailWithFile(String to, String subject, String message, InputStream inputStream);
}
