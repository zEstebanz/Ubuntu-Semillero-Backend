package com.semillero.ubuntu.Services.impl;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.semillero.ubuntu.Services.CloudinaryImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CloudinaryImageServiceImpl implements CloudinaryImageService {

    private final Cloudinary cloudinary;
    @Override
    public Map upload(MultipartFile file) {

        try {
            return cloudinary.uploader().upload(file.getBytes(), Map.of());
        } catch (Exception e){
            throw new RuntimeException("Image uploading fail!");
        }
    }

    @Override
    @Async("asyncTaskExecutor")
    public void delete(String public_id) {

        try {
            cloudinary.uploader().destroy(public_id, ObjectUtils.emptyMap());
        } catch (Exception e){
            throw new RuntimeException("Error deleting image!");
        }
    }

}
