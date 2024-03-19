package com.semillero.ubuntu.Services.impl;

import com.semillero.ubuntu.DTOs.ImageDto;
import com.semillero.ubuntu.Entities.Image;
import com.semillero.ubuntu.Repositories.ImageRepository;
import com.semillero.ubuntu.Services.ImageService;
import com.semillero.ubuntu.Utils.Mapper;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;
    private final CloudinaryImageServiceImpl cloudinaryImageService;

    @Override
    public ImageDto createImage(MultipartFile file) {

        Map upload = cloudinaryImageService.upload(file);
        Image image = Image.createImage(upload);
        imageRepository.save(image);

        return Mapper.imageToImageDto(image);
    }

    @Override
    public void deleteImage(Long id) {

        Image image = imageRepository.findById(id)
                .orElseThrow(()-> new EntityNotFoundException("Image not found with ID: " + id));
        cloudinaryImageService.delete(image.getPublic_id());
        imageRepository.delete(image);
    }
}
