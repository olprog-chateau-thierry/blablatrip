package com.formation.blablatrip.services;

import com.formation.blablatrip.entities.UtilisateurEntity;
import com.formation.blablatrip.repositories.UtilisateurRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class UtilisateurService {

    private final UtilisateurRepository utilisateurRepository;

    public UtilisateurService(UtilisateurRepository utilisateurRepository) {
        this.utilisateurRepository = utilisateurRepository;
    }

    public Iterable<UtilisateurEntity> findAll() {
        return utilisateurRepository.findAll();
    }

    public UtilisateurEntity save(UtilisateurEntity utilisateur, boolean mustUpdate) throws Exception {
        // Vérification de notre Objet
        if (utilisateur == null) {
            throw new Exception("L'objet Utilisateur est nul");
        }
        Integer countUtilisateur = utilisateurRepository.countByEmailEquals(utilisateur.getEmail());
        if ((mustUpdate && countUtilisateur == 1) || (!mustUpdate && countUtilisateur == 0)) {
            return utilisateurRepository.save(utilisateur);
        }
        throw new Exception("L'utilisateur ne peut être inséré ou mis à jour.");
    }

    public void deleteById(Long id) {
        utilisateurRepository.deleteById(id);
    }

    public Optional<UtilisateurEntity> findById(Long id) throws Exception {
        if (id == null) {
            throw new Exception("L'id est nul. je ne peux lancer la requête");
        }
        return utilisateurRepository.findById(id);
    }
}
