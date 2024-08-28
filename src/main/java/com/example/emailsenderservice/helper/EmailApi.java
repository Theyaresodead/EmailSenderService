package com.example.emailsenderservice.helper;

import com.example.emailsenderservice.controller.api.EmailRequest;
import com.example.emailsenderservice.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

@RestController
@RequestMapping("api/v1/email")
public class EmailApi {

    @Autowired
    private EmailService emailService;

    @PostMapping("/send")
    public ResponseEntity<?> sendEmail(@RequestBody EmailRequest email) {
      emailService.sendEmailWithHtml(email.getTo(), email.getMessage(), email.getSubject());
      return ResponseEntity.ok(
              CustomResponse.builder().message("Email Sent").status(HttpStatus.OK).success(true).build()
      );
    }

   @PostMapping("/sendwithfile")
   public ResponseEntity<CustomResponse> sendEmailWithFile(@RequestPart EmailRequest email, @RequestPart MultipartFile file) throws IOException {

            emailService.sendEmailWithFile(email.getTo(), email.getMessage(), email.getSubject(),file.getInputStream());
            return ResponseEntity.ok(
             CustomResponse.builder().message("Email Sent").status(HttpStatus.OK).success(true).build()
            );

    }
}
