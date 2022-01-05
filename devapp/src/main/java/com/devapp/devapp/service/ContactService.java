package com.devapp.devapp.service;


import com.devapp.devapp.entities.Contact;

import java.util.List;

public interface ContactService {
    public Long ajouterContact(String subject, String from, String message);
    public Long modifierContact(Long contactId, String subject, String from, String message);
    public List<Contact> getAllContact();
    public void deleteContact(Long contactId);
    public Contact getContactById(Long contactId);
    Long nbContact();
}
