package com.formation.blablatrip.services;

import com.formation.blablatrip.entities.VoyageEntity;
import com.formation.blablatrip.repositories.VoyageRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VoyageService {

    private final VoyageRepository voyageRepository;

    public VoyageService(VoyageRepository voyageRepository) {
        this.voyageRepository = voyageRepository;
    }

    public VoyageEntity save(VoyageEntity destination) throws Exception {
        if (destination == null) {
            throw new Exception("Voyage est vide");
        }
        // sauvegarder en bdd
        return voyageRepository.save(destination);
    }

    public Iterable<VoyageEntity> findAll() {
        return voyageRepository.findAll();
    }

    public void deleteById(Long id) { voyageRepository.deleteById(id);}
    public Optional<VoyageEntity> findById(Long id) { return voyageRepository.findById(id);}
}
