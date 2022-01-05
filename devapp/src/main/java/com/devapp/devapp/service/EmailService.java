package com.devapp.devapp.service;

import javax.mail.MessagingException;

public interface EmailService {

    void sendComfirmationMessage(String email) throws MessagingException;
    void sendRestPasswordMessage(String email) throws MessagingException;

}
