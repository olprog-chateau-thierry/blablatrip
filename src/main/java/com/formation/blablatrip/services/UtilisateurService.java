package com.formation.blablatrip.services;

import com.formation.blablatrip.entities.UtilisateurEntity;
import com.formation.blablatrip.repositories.UtilisateurRepository;
import org.springframework.stereotype.Service;

@Service
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Iterable<UtilisateurEntity> findAll() {
        return utilisateurRepository.findAll();
    }

}
