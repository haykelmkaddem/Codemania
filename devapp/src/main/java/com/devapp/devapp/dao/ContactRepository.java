package com.devapp.devapp.dao;

import com.devapp.devapp.entities.Contact;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepository extends CrudRepository<Contact,Long> {

    @Query("select  count(c) from Contact c")
    public Long NBContact();
}
