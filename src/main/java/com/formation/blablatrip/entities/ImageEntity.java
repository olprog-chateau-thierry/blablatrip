package com.formation.blablatrip.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

@Data
@Entity
@Table(name = "image")
public class ImageEntity {

    @Transient
    private static final AtomicInteger counter = new AtomicInteger(0);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String link;

    @ManyToOne(optional = false)
    private VoyageEntity voyage;


    public void generateId(String extension) {
        String timestamp = String.valueOf(System.currentTimeMillis());
        String partialUUID = UUID.randomUUID().toString().substring(0, 8); // 8 premiers caractères
        int count = counter.incrementAndGet(); // Incrémentation du compteur

        this.link = "IMG_" + timestamp + "_" + partialUUID + "_" + count + "." + extension;
    }
}
