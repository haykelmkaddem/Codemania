package com.devapp.devapp.controller;

import com.devapp.devapp.entities.Actualite;
import com.devapp.devapp.service.ActualiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class ActualiteRestController {

    @Autowired
    ActualiteService actualiteService;

    @GetMapping(value = "/addActualite/{titre}/{description}")
    @ResponseBody
    public void ajouterActualite(@PathVariable("titre") String titre, @PathVariable("description") String description){
        actualiteService.ajouterActualite(titre,description);
    }

    @GetMapping(value = "/deleteActualite/{actualiteId}")
    @ResponseBody
    public void deleteeActualite(@PathVariable("actualiteId") Long actualiteId){
        actualiteService.deleteActualite(actualiteId);
    }

    @GetMapping(value = "/listActualite")
    @ResponseBody
    public List<Actualite> listActualite(){
        return actualiteService.getAllActualite();
    }

    @GetMapping(value = "/nbActualite")
    @ResponseBody
    public Long nbActualite(){
        return actualiteService.nbActualite();
    }
}
