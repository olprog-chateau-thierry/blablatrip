package com.formation.blablatrip.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.checkerframework.checker.regex.qual.Regex;
import org.hibernate.validator.constraints.Range;

import java.util.Collection;

@Data
@Entity
@Table(name = "destination")
public class DestinationEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    @Pattern(regexp = "^[\\p{L}\\s\\-\\_]{2,100}$")
    private String nom;

    @Column(nullable = false, columnDefinition = "LONGTEXT")
    @Size(min = 10, max = 100)
    private String description;

    @Column(nullable = false, length = 100)
    @Pattern(regexp = "^[\\p{L}\\s\\-]{2,100}$")
    private String pays;

    @Column(nullable = false, length = 100)
    @Pattern(regexp = "^[\\p{L}\\s\\-]{2,100}$")
    private String ville;

    @Positive
    private Double prixBase;

    @ToString.Exclude
    @OneToMany(mappedBy = "destination")
    private Collection<VoyageEntity> voyages;
}
