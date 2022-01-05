package com.devapp.devapp.service.impl;

import com.devapp.devapp.controller.dto.RegisterForm;
import com.devapp.devapp.dao.userrepository.RoleRepository;
import com.devapp.devapp.dao.userrepository.UserRepository;
import com.devapp.devapp.entities.userentity.Role;
import com.devapp.devapp.entities.userentity.User;
import com.devapp.devapp.service.AccountService;
import com.devapp.devapp.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import java.util.Date;
import java.util.List;


@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    @Autowired
    private EmailService emailService;


    @Override
    public User saveUser(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public Role saveRole(Role role)
    {
        return roleRepository.save(role);

    }

    @Override
    public User loadUserByUsername(String userName)
    {
        return userRepository.findByUsername(userName);
    }

    @Override
    public void addRoleToUser(String username, String roleName) {


        User user = userRepository.findByUsername(username);
        Role role = roleRepository.findByName(roleName);
        //puisque la methode Transactionnel ilajout le role ala table
        user.getRoles().add(role);

    }

    @Override
    public List<User> getAllUser() {
        return (List<User>) userRepository.findAll();
    }


    @Override
    public void deleteUserById(long userId) {
        userRepository.deleteById(userId);
    }

    @Override
    public void updateMotDePasse(String email, String password) {
        User user = loadUserByEmail(email);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        userRepository.save(user);
    }

    @Override
    public User loadUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
    @Override
    public void resetPassword(RegisterForm userForm) throws MessagingException {
        User usermail = loadUserByEmail(userForm.getEmail());
        if (usermail == null) throw new RuntimeException("this mail is not exists");
        emailService.sendRestPasswordMessage(usermail.getEmail());
    }

    @Override
    public void comfirm(Authentication authentication) {
        String email = (String) authentication.getPrincipal();
        User usermail = loadUserByEmail(email);
        if (usermail == null) throw new RuntimeException("this mail is not exists");
        usermail.setActivated(true);
        userRepository.save(usermail);
    }

    @Override
    public User register(RegisterForm userForm) throws MessagingException {
        if (!userForm.getPassword().equals(userForm.getRepassword()))
            throw new RuntimeException("you must confirm your password");

        User username=loadUserByUsername(userForm.getUsername());
        if (username!=null) throw new RuntimeException("this User is already exists");
        User usermail=loadUserByEmail(userForm.getEmail());
        if (usermail!=null) throw new RuntimeException("this mail is already exists");
        User user = new User();
        user.setEmail(userForm.getEmail());
        user.setUsername(userForm.getUsername());
        user.setPassword(userForm.getPassword());
        user.setPhoneNumber(userForm.getNumPhone());
        user.setActivated(false);
        saveUser(user);
        addRoleToUser(userForm.getUsername(),"USER");

        emailService.sendComfirmationMessage(userForm.getEmail());
        return user;
    }

    @Override
    public void ajouterUser(String username, String firstName, String lastName, String adresse, String birthDate, String email, String phoneNumber, String password, String role) {
        User user = new User();
        user.setUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAdresse(adresse);
        user.setBirthDate(birthDate);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setActivated(true);
        userRepository.save(user);
        addRoleToUser(user.getUsername(),role);
    }

    @Override
    public void modifierUser(String username, String firstName, String lastName, String adresse, String birthDate, String email, String phoneNumber) {
        User user = userRepository.findByUsername(username);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setAdresse(adresse);
        user.setBirthDate(birthDate);
        user.setEmail(email);
        user.setPhoneNumber(phoneNumber);
        userRepository.save(user);
        user.setUsername(username);
        userRepository.save(user);
    }

    @Override
    public void deleteUserByUsername(String username) {
        User user = userRepository.findByUsername(username);
        userRepository.deleteById(user.getUserId());
    }

    @Override
    public Long nbUsers() {
        return userRepository.NBUser();
    }

    @Override
    public void updatefirstNameAndlastById(String firstName, String lastName, Long userId) {
        User user = userRepository.findById(userId).get();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        userRepository.save(user);
    }
}
