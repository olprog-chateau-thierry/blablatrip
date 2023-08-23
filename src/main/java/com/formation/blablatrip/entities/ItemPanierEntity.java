package com.formation.blablatrip.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "item_panier")
public class ItemPanierEntity {

    @EmbeddedId
    private ItemPanierId id;

    @ManyToOne
    @MapsId("idPanier")
    @JoinColumn(name = "id_panier")
    private PanierEntity panier;

    @ManyToOne
    @MapsId("idVoyage")
    @JoinColumn(name = "id_voyage")
    private VoyageEntity voyage;

    @Column(nullable = false, columnDefinition = "SMALLINT DEFAULT 1")
    private Integer quantite;

    private Double sousTotal;

    private LocalDateTime dateAjout;
}
