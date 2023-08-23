package com.formation.blablatrip.services;

import com.formation.blablatrip.entities.DestinationEntity;
import com.formation.blablatrip.repositories.DestinationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DestinationService {

    private final DestinationRepository destinationRepository;

    public DestinationService(DestinationRepository destinationRepository) {
        this.destinationRepository = destinationRepository;
    }

    public DestinationEntity save(DestinationEntity destination) throws Exception {
        if (destination == null) {
            throw new Exception("Destination est vide");
        }
        // sauvegarder en bdd
        return destinationRepository.save(destination);
    }

    public Iterable<DestinationEntity> findAll() {
        return destinationRepository.findAll();
    }
}
