package com.devapp.devapp.dao;

import com.devapp.devapp.entities.Actualite;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActualiteRepository extends CrudRepository<Actualite, Long> {
    @Query("select  count(c) from Actualite c")
    public Long NBActualite();
}
