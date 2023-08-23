package com.formation.blablatrip.entities;

import com.formation.blablatrip.enums.UtilisateurTypeEnum;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Entity
@Table(name = "utilisateur")
public class UtilisateurEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nom;

    @Column(nullable = false, length = 100)
    private String prenom;

    @Column(nullable = false, length = 200)
    private String email;

    @Column(nullable = false, length = 100)
    private String mdp;

    @Transient
    private String verifMdp;

    @Enumerated(EnumType.STRING)
    private UtilisateurTypeEnum type;

    private LocalDateTime dateInscription;

    private LocalDateTime dateDesinscription;

    @OneToOne
    private PanierEntity panier;

    @OneToMany(mappedBy = "utilisateur")
    private Collection<AvisEntity> aviss;

    @OneToMany(mappedBy = "utilisateur")
    private Collection<ReservationEntity> reservations;
}
