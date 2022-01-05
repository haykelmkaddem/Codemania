package com.devapp.devapp.service.impl;

import com.devapp.devapp.dao.ActualiteRepository;
import com.devapp.devapp.entities.Actualite;
import com.devapp.devapp.service.ActualiteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ActualiteServiceImpl implements ActualiteService {

    @Autowired
    ActualiteRepository actualiteRepository;

    @Override
    public Long ajouterActualite(String title, String description) {
        Actualite actualite = new Actualite();
        actualite.setTitle(title);
        actualite.setDescription(description);
        actualiteRepository.save(actualite);
        return actualite.getActualiteId();
    }

    @Override
    public Long modifierActualite(Long actualiteId, String title, String description) {
        Actualite actualite = actualiteRepository.findById(actualiteId).get();
        actualite.setTitle(title);
        actualite.setDescription(description);
        actualiteRepository.save(actualite);
        return actualite.getActualiteId();
    }

    @Override
    public List<Actualite> getAllActualite() {
        return (List<Actualite>) actualiteRepository.findAll();
    }

    @Override
    public void deleteActualite(Long actualiteId) {
        actualiteRepository.deleteById(actualiteId);
    }

    @Override
    public Actualite getActualiteById(Long actualiteId) {
        Actualite actualite =actualiteRepository.findById(actualiteId).get();
        return actualite;
    }

    @Override
    public Long nbActualite() {
        return actualiteRepository.NBActualite();
    }
}
