package com.devapp.devapp.service.impl;

import com.devapp.devapp.dao.ProduitRepository;
import com.devapp.devapp.entities.Produit;
import com.devapp.devapp.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProduitServiceImpl implements ProduitService {

    @Autowired
    ProduitRepository produitRepository;

    @Override
    public Long ajouterProduit(String title, String description) {
        Produit produit =new Produit();
        produit.setTitle(title);
        produit.setDescription(description);
        produitRepository.save(produit);
        return produit.getProduitId();
    }

    @Override
    public Long modifierProduit(Long produitId, String title, String description) {
        Produit produit = produitRepository.findById(produitId).get();
        produit.setTitle(title);
        produit.setDescription(description);
        produitRepository.save(produit);
        return produit.getProduitId();
    }

    @Override
    public List<Produit> getAllProduit() {
        return (List<Produit>) produitRepository.findAll();
    }

    @Override
    public void deleteProduit(Long produitId) {
        produitRepository.deleteById(produitId);
    }

    @Override
    public Produit getProduitById(Long produitId) {
        Produit produit = produitRepository.findById(produitId).get();
        return produit;
    }

    @Override
    public Long nbProduit() {
        return produitRepository.NBProduit();
    }
}
