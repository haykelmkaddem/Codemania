package com.devapp.devapp.controller.dto;

import lombok.Data;

@Data
public class RegisterForm {
    private String email;
    private String username;
    private String password;
    private String repassword;
    private String numPhone;
}
