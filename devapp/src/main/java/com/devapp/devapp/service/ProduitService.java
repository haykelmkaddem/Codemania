package com.devapp.devapp.service;


import com.devapp.devapp.entities.Produit;

import java.util.List;

public interface ProduitService {
    public Long ajouterProduit(String title, String description);
    public Long modifierProduit(Long produitId, String title, String description);
    public List<Produit> getAllProduit();
    public void deleteProduit(Long produitId);
    public Produit getProduitById(Long produitId);
    Long nbProduit();
}
