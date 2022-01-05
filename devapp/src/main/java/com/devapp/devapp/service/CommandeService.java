package com.devapp.devapp.service;


import com.devapp.devapp.entities.Commande;

import java.util.List;

public interface CommandeService {

    public Long ajouterCommande(String username, Long produitId);
    public List<Commande> getAllCommande();
    public void deleteCommande(Long commandeId);
    public Commande getCommandeById(Long commandeId);
    public List<Commande> getAllCommandeByUser(String username);
    Long nbCommandes();
}
