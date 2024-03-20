package com.semillero.ubuntu.DTOs;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public record PublicationEditRequest(
        String tittle,
        String description,
        List<Long> id_imageToReplace,
        List<MultipartFile> newImages
) { }
