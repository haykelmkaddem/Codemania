package com.devapp.devapp.service.impl;


import com.devapp.devapp.entities.EmailType;
import com.devapp.devapp.security.SecurityConstants;
import com.devapp.devapp.service.EmailService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.*;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender emailSender;

    private void sendSimpleMessage(String to, EmailType emailType) throws MessagingException {
        String token =generateToken(to, emailType.name(), emailType.getExpirationTokenTime());
        MimeMessage message = emailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
        helper.setTo(to);
        helper.setSubject(emailType.getSubject());
        helper.setText("<a href=\"http://localhost:8080/"+emailType.getPath()+"?token="+token+"\">click to "+ emailType.getSubject() +"</a>", true);
        emailSender.send(message);
    }

    @Override
    public void sendComfirmationMessage(String email) throws MessagingException {
        sendSimpleMessage(email,EmailType.CONFIRAMTION);
    }

    @Override
    public void sendRestPasswordMessage(String email) throws MessagingException {
        sendSimpleMessage(email,EmailType.PASSWORD);
    }

    private String generateToken(String to, String type, long expirationTime){
        Map<String,String> authority = new HashMap<>();
        authority.put("authority", type);
        return Jwts.builder()
                .setSubject(to)
                .setExpiration(new Date(System.currentTimeMillis()+ expirationTime))
                .signWith(SignatureAlgorithm.HS256, SecurityConstants.SecretKey)
                .claim("roles", Collections.singletonList(authority)).compact();
    }
}
