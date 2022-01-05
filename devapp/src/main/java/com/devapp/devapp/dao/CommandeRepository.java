package com.devapp.devapp.dao;

import com.devapp.devapp.entities.Commande;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommandeRepository extends CrudRepository<Commande,Long> {

    @Query("select DISTINCT c from Commande c "
            + "join c.user u "
            + "where u.userId=:userId "
    )
    public List<Commande> getAllCommandeByUser(@Param("userId") Long userId);

    @Query("select  count(c) from Commande c")
    public Long NBCommande();
}
