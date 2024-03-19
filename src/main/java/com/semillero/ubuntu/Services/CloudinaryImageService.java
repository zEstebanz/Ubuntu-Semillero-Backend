package com.semillero.ubuntu.Services;

import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

public interface CloudinaryImageService {

    Map upload(MultipartFile file);
    void delete(String public_id);
}
