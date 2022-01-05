package com.devapp.devapp.service;

import com.devapp.devapp.controller.dto.RegisterForm;
import com.devapp.devapp.entities.userentity.Role;
import com.devapp.devapp.entities.userentity.User;
import org.springframework.security.core.Authentication;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;


public interface AccountService  {
     User saveUser(User user);
     Role saveRole(Role role);
     User loadUserByUsername(String username);
     void addRoleToUser(String username,String rolename);
     List<User> getAllUser();
     void deleteUserById(long userId);
     void updateMotDePasse(String email, String password);
     User loadUserByEmail(String email);
    void updatefirstNameAndlastById(String firstName, String lastName, Long userId);
    void resetPassword(RegisterForm userForm) throws MessagingException;

    void  comfirm(Authentication authentication);

    User register(RegisterForm userForm) throws MessagingException;

    public  void ajouterUser(String username, String firstName, String lastName, String adresse, String birthDate,String email,String phoneNumber,String password,String role);

    public  void modifierUser(String username, String firstName, String lastName, String adresse, String birthDate,String email,String phoneNumber);
    void deleteUserByUsername(String username);

    Long nbUsers();
}
