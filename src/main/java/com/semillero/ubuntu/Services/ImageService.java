package com.semillero.ubuntu.Services;

import com.semillero.ubuntu.DTOs.ImageDto;
import org.springframework.web.multipart.MultipartFile;

public interface ImageService {
    ImageDto createImage(MultipartFile file);
    void deleteImage(Long id);
}
