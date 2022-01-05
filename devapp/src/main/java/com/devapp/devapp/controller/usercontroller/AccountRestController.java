package com.devapp.devapp.controller.usercontroller;


import com.devapp.devapp.controller.dto.RegisterForm;
import com.devapp.devapp.entities.userentity.User;
import com.devapp.devapp.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("user")
public class AccountRestController {


    @Autowired
    private AccountService accountService;




    @PostMapping("/register")
    public User register(@RequestBody RegisterForm userForm) throws MessagingException {

        return accountService.register(userForm);
    }

    @GetMapping(value = "/getAllUsers")
    public List<User> getAllUser() {
        return accountService.getAllUser();
    }

    @DeleteMapping("/deleteUserById/{id}")
    public void deleteUserById(@PathVariable("id") int UserId) {
        accountService.deleteUserById(UserId);
    }

  //  Modifier mdp : http://localhost:8081/dari/updatepassword/1/password

    @PutMapping(value = "/updatepassword/{password}")
    public void updateMotDePasse(@PathVariable("password") String password, Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        accountService.updateMotDePasse(email,password);
    }

    @PatchMapping("/forgetpassword")
    public void forgetpassword(@RequestBody RegisterForm userForm) throws MessagingException {
        accountService.resetPassword(userForm);
    }

    @PatchMapping("/confirmaccount")
    public void confirmAccount(Authentication authentication) {
        accountService.comfirm(authentication);
    }


    @GetMapping(value = "/addUser/{username}/{firstName}/{lastName}/{adresse}/{birthDate}/{email}/{phoneNumber}/{password}/{role}")
    @ResponseBody
    public void ajouterUser(@PathVariable("username") String username, @PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName, @PathVariable("adresse") String adresse, @PathVariable("birthDate") String birthDate, @PathVariable("email") String email, @PathVariable("phoneNumber") String phoneNumber, @PathVariable("password") String password, @PathVariable("role") String role){
        accountService.ajouterUser(username, firstName, lastName, adresse, birthDate, email, phoneNumber, password, role);
    }

    @GetMapping(value = "/updateUser/{username}/{firstName}/{lastName}/{adresse}/{birthDate}/{email}/{phoneNumber}")
    @ResponseBody
    public void modifierUser(@PathVariable("username") String username, @PathVariable("firstName") String firstName, @PathVariable("lastName") String lastName, @PathVariable("adresse") String adresse, @PathVariable("birthDate") String birthDate, @PathVariable("email") String email, @PathVariable("phoneNumber") String phoneNumber){
        accountService.modifierUser(username, firstName, lastName, adresse, birthDate, email, phoneNumber);
    }

    @GetMapping(value = "/findUser/{username}")
    @ResponseBody
    public User findUser(@PathVariable("username") String username){
        return accountService.loadUserByUsername(username);
    }

    @GetMapping(value = "/deleteUser/{username}")
    @ResponseBody
    public void deleteUser(@PathVariable("username") String username){
        accountService.deleteUserByUsername(username);
    }

    @GetMapping(value = "/nbUser")
    @ResponseBody
    public Long nbUser(){
        return accountService.nbUsers();
    }
}
