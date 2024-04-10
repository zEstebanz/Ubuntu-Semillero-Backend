package com.semillero.ubuntu.Utils;

import com.semillero.ubuntu.DTOs.*;
import com.semillero.ubuntu.Entities.Image;
import com.semillero.ubuntu.Entities.Mensaje;
import com.semillero.ubuntu.Entities.Microemprendimiento;
import com.semillero.ubuntu.Entities.Publicacion;
import jakarta.persistence.Tuple;

import java.util.List;
import java.util.stream.Collectors;

public class Mapper {

    public static PublicationResponse publicationToPublicationResponse(Publicacion publicacion, List<Image> images){

        List<String> getUrl = images.stream().map(Image::getSecure_url).toList();

        return new PublicationResponse(
                        publicacion.getId(),
                        publicacion.getTitulo(),
                        publicacion.getDescripcion(),
                        publicacion.getIsDeleted(),
                        publicacion.getUsuarioCreador().getId(),
                        publicacion.getFechaCreacion(),
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
                MapperUtil.mapToDto(microemprendimiento.getPais(), PaisDTO.class),
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
    public static MensajeResponseDTO mensajeToResponse(Mensaje mensaje){
        MicroemprendimientoResponse microemprendimientoResponse=Mapper.microemprendimientoToResponse(mensaje.getMicroemprendimiento(), mensaje.getMicroemprendimiento().getImages());
        return new MensajeResponseDTO(
                mensaje.getId(),
                mensaje.getFechaCreacion(),
                mensaje.isGestionado(),
                mensaje.getTelefono(),
                mensaje.getApellido() +", "+ mensaje.getNombre(),
                mensaje.getEmail(),
                mensaje.getTexto(),
                microemprendimientoResponse
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
                        (Long) resultado[0]
                ))
                .collect(Collectors.toList());
    }
    public static MensajeEstadisticaDTO tupleToMensajeEstadisticaDTO(Tuple resultado){
        if (resultado == null) {
            return new MensajeEstadisticaDTO(0L, 0L);
        } else {
            Long cantGestionados = resultado.get("cantGestionados", Long.class);
            Long cantNoGestionados = resultado.get("cantNoGestionados", Long.class);
            return new MensajeEstadisticaDTO(cantGestionados, cantNoGestionados);
        }
    }
}

