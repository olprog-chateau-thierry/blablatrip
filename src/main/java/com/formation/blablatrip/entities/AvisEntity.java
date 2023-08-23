package com.formation.blablatrip.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "avis")
public class AvisEntity {

    @EmbeddedId
    private AvisId id;

    @ManyToOne
    @MapsId("idUtilisateur")
    @JoinColumn(name = "id_utilisateur")
    private UtilisateurEntity utilisateur;

    @ManyToOne
    @MapsId("idVoyage")
    @JoinColumn(name = "id_voyage")
    private VoyageEntity voyage;

    @Column(columnDefinition = "TEXT")
    private String commentaire;

    @Column(columnDefinition = "TINYINT")
    private Integer note;
}
