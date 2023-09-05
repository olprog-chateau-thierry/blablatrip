package com.formation.blablatrip.repositories;

import com.formation.blablatrip.entities.ImageEntity;
import com.formation.blablatrip.entities.VoyageEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;

public interface ImageRepository extends CrudRepository<ImageEntity, Long> {
    Collection<ImageEntity> findByVoyage(VoyageEntity voyage);
}
