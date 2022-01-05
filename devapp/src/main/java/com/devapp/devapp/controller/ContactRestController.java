package com.devapp.devapp.controller;

import com.devapp.devapp.entities.Actualite;
import com.devapp.devapp.entities.Contact;
import com.devapp.devapp.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class ContactRestController {
    @Autowired
    ContactService contactService;

    @GetMapping(value = "/addContact/{subject}/{from}/{message}")
    @ResponseBody
    public void ajouterContact(@PathVariable("subject") String subject, @PathVariable("from") String from, @PathVariable("message") String message){
        contactService.ajouterContact(subject,from,message);
    }

    @GetMapping(value = "/listContact")
    @ResponseBody
    public List<Contact> listContact(){
        return contactService.getAllContact();
    }

    @GetMapping(value = "/nbContact")
    @ResponseBody
    public Long nbContact(){
        return contactService.nbContact();
    }
}
