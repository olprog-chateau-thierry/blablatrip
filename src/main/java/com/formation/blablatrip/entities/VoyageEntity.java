package com.formation.blablatrip.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.Collection;

@Data
@Entity
@Table(name = "voyage")
public class VoyageEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull
    private LocalDateTime dateDepart;

    @Column(nullable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    @NotNull
    private LocalDateTime dateRetour;

    @Column(nullable = false)
    @NotNull
    private Double prix;

    @Column(nullable = false)
    @NotNull
    @Min(1)
    private Integer placesDisponibles;

    @OneToMany(mappedBy = "voyage")
    private Collection<AvisEntity> aviss;

    @OneToMany(mappedBy = "voyage")
    private Collection<ItemPanierEntity> itemPaniers;

    @OneToMany(mappedBy = "voyage")
    private Collection<ReservationEntity> reservations;

    @ManyToOne(optional = false)
    private DestinationEntity destination;

    @OneToMany(mappedBy = "voyage")
    private Collection<ImageEntity> imageList;
}
