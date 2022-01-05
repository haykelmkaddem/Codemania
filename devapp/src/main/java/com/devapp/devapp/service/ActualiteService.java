package com.devapp.devapp.service;

import com.devapp.devapp.entities.Actualite;

import java.util.List;

public interface ActualiteService {

    public Long ajouterActualite(String title, String description);
    public Long modifierActualite(Long actualiteId, String title, String description);
    public List<Actualite> getAllActualite();
    public void deleteActualite(Long actualiteId);
    public Actualite getActualiteById(Long actualiteId);

    Long nbActualite();
}
