package com.formation.blablatrip.repositories;

import com.formation.blablatrip.entities.DestinationEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

public interface DestinationRepository
        extends CrudRepository<DestinationEntity, Long> {
}
