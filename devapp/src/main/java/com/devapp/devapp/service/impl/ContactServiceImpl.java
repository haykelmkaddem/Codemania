package com.devapp.devapp.service.impl;

import com.devapp.devapp.dao.ContactRepository;
import com.devapp.devapp.entities.Contact;
import com.devapp.devapp.service.ContactService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    @Autowired
    ContactRepository contactRepository;

    @Override
    public Long ajouterContact(String subject, String from, String message) {
        Contact contact = new Contact();
        contact.setSubject(subject);
        contact.setFrom(from);
        contact.setMessage(message);
        contactRepository.save(contact);
        return contact.getContactId();
    }

    @Override
    public Long modifierContact(Long contactId, String subject, String from, String message) {
        Contact contact = contactRepository.findById(contactId).get();
        contact.setSubject(subject);
        contact.setFrom(from);
        contact.setMessage(message);
        contactRepository.save(contact);
        return contact.getContactId();
    }

    @Override
    public List<Contact> getAllContact() {
        return (List<Contact>) contactRepository.findAll();
    }

    @Override
    public void deleteContact(Long contactId) {
        contactRepository.deleteById(contactId);
    }

    @Override
    public Contact getContactById(Long contactId) {
        Contact contact = contactRepository.findById(contactId).get();
        return contact;
    }

    @Override
    public Long nbContact() {
        return contactRepository.NBContact();
    }
}
