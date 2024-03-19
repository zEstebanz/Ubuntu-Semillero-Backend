package com.semillero.ubuntu.Utils;

import com.semillero.ubuntu.DTOs.ImageDto;
import com.semillero.ubuntu.DTOs.PublicationResponse;
import com.semillero.ubuntu.Entities.Image;
import com.semillero.ubuntu.Entities.Publicacion;

import java.util.List;

public class Mapper {

    public static PublicationResponse publicationToPublicationResponse(Publicacion publicacion, List<Image> images){

        List<String> getUrl = images.stream().map(Image::getSecure_url).toList();

        return new PublicationResponse(
                publicacion.getTitulo(),
                publicacion.getDescripcion(),
                publicacion.getIsDeleted(),
                publicacion.getUsuarioCreador().getId(),
                getUrl
        );
    }

    public static ImageDto imageToImageDto(Image image){

        return new ImageDto(
                image.getId(),
                image.getSecure_url(),
                image.getFormat(),
                image.getCreated_at(),
                image.getPublic_id(),
                image.getWidth(),
                image.getHeight()
        );
    }
}
