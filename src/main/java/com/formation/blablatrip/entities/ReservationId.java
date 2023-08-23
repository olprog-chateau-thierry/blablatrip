package com.formation.blablatrip.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
public class ReservationId implements Serializable {

    private Long idUtilisateur;

    private Long idVoyage;

}

