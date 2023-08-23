package com.formation.blablatrip.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;


@Embeddable
public class ItemPanierId implements Serializable {
    private Long idPanier;

    private Long idVoyage;
}
