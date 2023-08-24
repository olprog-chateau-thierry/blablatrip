package com.formation.blablatrip.repositories;

import com.formation.blablatrip.entities.PanierEntity;
import com.formation.blablatrip.entities.UtilisateurEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UtilisateurRepository
        extends CrudRepository<UtilisateurEntity, Long> {
    // SELECT COUNT(*) FROM utilisateur WHERE email = ?
    // @Query(value = "SELECT COUNT(*) FROM utilisateur WHERE email = ?1", nativeQuery = true)
    Integer countByEmailEquals(String email);
}
