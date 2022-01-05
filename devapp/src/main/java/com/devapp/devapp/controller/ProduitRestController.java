package com.devapp.devapp.controller;

import com.devapp.devapp.entities.Produit;
import com.devapp.devapp.service.ProduitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class ProduitRestController {
    @Autowired
    ProduitService produitService;

    @GetMapping(value = "/addProduit/{titre}/{description}")
    @ResponseBody
    public void ajouterProduit(@PathVariable("titre") String titre, @PathVariable("description") String description){
        produitService.ajouterProduit(titre,description);
    }

    @GetMapping(value = "/updateProduit/{produitId}/{titre}/{description}")
    @ResponseBody
    public void modifierProduit(@PathVariable("produitId") Long produitId, @PathVariable("titre") String titre, @PathVariable("description") String description){
        produitService.modifierProduit(produitId,titre,description);
    }

    @GetMapping(value = "/deleteProduit/{produitId}")
    @ResponseBody
    public void deleteeProduit(@PathVariable("produitId") Long produitId){
        produitService.deleteProduit(produitId);
    }

    @GetMapping(value = "/findProduit/{produitId}")
    @ResponseBody
    public Produit findProduitById(@PathVariable("produitId") Long produitId){
        return produitService.getProduitById(produitId);
    }

    @GetMapping(value = "/listProduit")
    @ResponseBody
    public List<Produit> listProduit(){
        return produitService.getAllProduit();
    }

    @GetMapping(value = "/nbProduit")
    @ResponseBody
    public Long nbProduit(){
        return produitService.nbProduit();
    }
}
