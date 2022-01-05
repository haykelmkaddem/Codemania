package com.devapp.devapp.controller;

import com.devapp.devapp.entities.Commande;
import com.devapp.devapp.entities.Contact;
import com.devapp.devapp.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class CommandeRestController {
    @Autowired
    CommandeService commandeService;

    @GetMapping(value = "/addCommande/{username}/{produitId}")
    @ResponseBody
    public void ajouterCommande(@PathVariable("username") String username, @PathVariable("produitId") Long produitId){
        commandeService.ajouterCommande(username, produitId);
    }

    @GetMapping(value = "/listCommande")
    @ResponseBody
    public List<Commande> listCommande(){
        return commandeService.getAllCommande();
    }

    @GetMapping(value = "/listCommandeUser/{username}")
    @ResponseBody
    public List<Commande> listCommandeByUser(@PathVariable("username") String username){
        return commandeService.getAllCommandeByUser(username);
    }

    @GetMapping(value = "/nbCommande")
    @ResponseBody
    public Long nbCommande(){
        return commandeService.nbCommandes();
    }
}
