package com.devapp.devapp;

import com.devapp.devapp.entities.userentity.Role;
import com.devapp.devapp.entities.userentity.User;
import com.devapp.devapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class DevappApplication implements CommandLineRunner {
    @Autowired
    private AccountService accountService;


    public static void main(String[] args) {
        SpringApplication.run(DevappApplication.class, args);
    }

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void run(String... args) throws Exception {
        accountService.saveUser(new User(null,"admin","admin","marouadaikhi23@gmail.com",true,null));
        accountService.saveUser(new User(null,"user","user",true,null));
        accountService.saveUser(new User(null,"startup","startup",true,null));
        accountService.saveRole(new Role(null,"ADMIN"));
        accountService.saveRole(new Role(null,"CLIENT"));
        accountService.saveRole(new Role(null,"STARTUP"));
        accountService.addRoleToUser("admin","ADMIN");
        accountService.addRoleToUser("user","CLIENT");
        accountService.addRoleToUser("startup","STARTUP");
    }
}
