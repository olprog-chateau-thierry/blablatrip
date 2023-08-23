package com.formation.blablatrip.entities;

import com.formation.blablatrip.enums.ReservationStatutEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "reservation")
public class ReservationEntity {

    @EmbeddedId
    private ReservationId id;

    @ManyToOne
    @MapsId("idUtilisateur")
    @JoinColumn(name = "id_utilisateur")
    private UtilisateurEntity utilisateur;

    @ManyToOne
    @MapsId("idVoyage")
    @JoinColumn(name = "id_voyage")
    private VoyageEntity voyage;

    private LocalDateTime dateReservation;

    private Integer nombrePersonnes;

    @Enumerated(EnumType.STRING)
    private ReservationStatutEnum statut;

    private Double prixTotal;
}
