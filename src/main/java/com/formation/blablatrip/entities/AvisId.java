package com.formation.blablatrip.entities;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class AvisId implements Serializable {

    private Long idUtilisateur;

    private Long idVoyage;
}
