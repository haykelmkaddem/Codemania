package com.devapp.devapp.dao;

import com.devapp.devapp.entities.Produit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProduitRepository extends CrudRepository<Produit, Long> {

    @Query("select  count(c) from Produit c")
    public Long NBProduit();
}
