package com.formation.blablatrip.repositories;

import com.formation.blablatrip.entities.DestinationEntity;
import com.formation.blablatrip.entities.PanierEntity;
import org.springframework.data.repository.CrudRepository;

public interface PanierRepository
        extends CrudRepository<PanierEntity, Long> {
}
