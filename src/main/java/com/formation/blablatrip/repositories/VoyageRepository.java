package com.formation.blablatrip.repositories;

import com.formation.blablatrip.entities.PanierEntity;
import com.formation.blablatrip.entities.VoyageEntity;
import org.springframework.data.repository.CrudRepository;

public interface VoyageRepository
        extends CrudRepository<VoyageEntity, Long> {
}
