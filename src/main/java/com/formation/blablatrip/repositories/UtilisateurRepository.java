package com.formation.blablatrip.repositories;

import com.formation.blablatrip.entities.PanierEntity;
import com.formation.blablatrip.entities.UtilisateurEntity;
import org.springframework.data.repository.CrudRepository;

public interface UtilisateurRepository
        extends CrudRepository<UtilisateurEntity, Long> {
}
