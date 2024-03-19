package com.semillero.ubuntu.Controllers;

import com.semillero.ubuntu.DTOs.ImageDto;
import com.semillero.ubuntu.Services.impl.ImageServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequiredArgsConstructor
@RequestMapping("/image")
public class ImageController {

    private final ImageServiceImpl imageService;

    @PostMapping("/create")
    public ResponseEntity<ImageDto> createImage(@RequestParam("image") MultipartFile file){

        return ResponseEntity.ok(imageService.createImage(file));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable Long id){
        imageService.deleteImage(id);
        return ResponseEntity.ok().build();
    }
}
