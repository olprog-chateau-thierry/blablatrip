package com.formation.blablatrip.entities;


import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Entity
@Table(name = "panier")
public class PanierEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dateCreation;

    @OneToOne(optional = false, mappedBy = "panier")
    private UtilisateurEntity utilisateur;

    @OneToMany(mappedBy = "panier")
    private Collection<ItemPanierEntity> itemPaniers;
}
