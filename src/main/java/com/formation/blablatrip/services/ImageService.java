package com.formation.blablatrip.services;

import com.formation.blablatrip.entities.ImageEntity;
import com.formation.blablatrip.entities.VoyageEntity;
import com.formation.blablatrip.repositories.ImageRepository;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public ImageEntity save(ImageEntity image) {
        return imageRepository.save(image);
    }

    public Collection<ImageEntity> findByVoyage(VoyageEntity voyage){
        return imageRepository.findByVoyage(voyage);
    }
}
