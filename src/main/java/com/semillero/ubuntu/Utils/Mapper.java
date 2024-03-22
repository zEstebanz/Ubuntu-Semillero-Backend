package com.semillero.ubuntu.Utils;

import com.semillero.ubuntu.DTOs.*;
import com.semillero.ubuntu.Entities.Image;
import com.semillero.ubuntu.Entities.Microemprendimiento;
import com.semillero.ubuntu.Entities.Publicacion;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public static MicroemprendimientoResponse microemprendimientoToResponse(Microemprendimiento microemprendimiento, List<Image> images){

        List<String> getUrl = images.stream().map(Image::getSecure_url).toList();

        return new MicroemprendimientoResponse(
                microemprendimiento.getId(),
                microemprendimiento.getNombre(),
                microemprendimiento.getRubro(),
                microemprendimiento.getSubrubro(),
                microemprendimiento.getPais(),
                microemprendimiento.getProvincia(),
                microemprendimiento.getCiudad(),
                microemprendimiento.getDescripcion(),
                microemprendimiento.getMasInfo(),
                microemprendimiento.getDeleted(),
                microemprendimiento.getGestionado(),
                getUrl,
                microemprendimiento.getFechaCreacion()
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
    public static List<RubroResponse> objectToRubroDTO(List<Object[]> resultados){
        return resultados.stream()
                .map(resultado -> new RubroResponse(
                        (Long) resultado[0],
                        (String) resultado[1],
                        (Long) resultado[2]
                ))
                .collect(Collectors.toList());
    }
    public static List<MicroemprendimientoEstadistica> objectToEstadisticaDTO(List<Object[]> resultados){
        return resultados.stream()
                .map(resultado -> new MicroemprendimientoEstadistica(
                        (Long) resultado[0],
                        (Long) resultado[1],
                        (Long) resultado[2]
                ))
                .collect(Collectors.toList());
    }
}
