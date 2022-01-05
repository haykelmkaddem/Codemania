package com.devapp.devapp.service.impl;

import com.devapp.devapp.dao.CommandeRepository;
import com.devapp.devapp.dao.ProduitRepository;
import com.devapp.devapp.dao.userrepository.UserRepository;
import com.devapp.devapp.entities.Commande;
import com.devapp.devapp.entities.Produit;
import com.devapp.devapp.entities.userentity.User;
import com.devapp.devapp.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CommandeServiceImpl implements CommandeService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    ProduitRepository produitRepository;
    @Autowired
    CommandeRepository commandeRepository;

    @Override
    public Long ajouterCommande(String username, Long produitId) {
        User user = userRepository.findByUsername(username);
        Produit produit = produitRepository.findById(produitId).get();
        Commande commande = new Commande();
        commande.setProduit(produit);
        commande.setUser(user);
        commande.setDate(new Date());
        commandeRepository.save(commande);
        return commande.getCommandeId();
    }

    @Override
    public List<Commande> getAllCommande() {
        return (List<Commande>) commandeRepository.findAll();
    }

    @Override
    public void deleteCommande(Long commandeId) {
        commandeRepository.deleteById(commandeId);
    }

    @Override
    public Commande getCommandeById(Long commandeId) {
        Commande commande = commandeRepository.findById(commandeId).get();
        return commande;
    }

    @Override
    public List<Commande> getAllCommandeByUser(String username) {
        User user = userRepository.findByUsername(username);
        return commandeRepository.getAllCommandeByUser(user.getUserId());
    }

    @Override
    public Long nbCommandes() {
        return commandeRepository.NBCommande();
    }
}
