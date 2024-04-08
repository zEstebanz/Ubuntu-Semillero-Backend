package com.semillero.ubuntu.Services;

import com.semillero.ubuntu.DTOs.AddImageToPublication;
import com.semillero.ubuntu.DTOs.PublicacionDTO;
import com.semillero.ubuntu.DTOs.PublicationEditRequest;
import com.semillero.ubuntu.DTOs.PublicationResponse;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public interface PublicacionService {

    List<PublicationResponse> getAll() throws Exception;
    List<PublicationResponse> traerPublisNoOcultas() throws Exception;
    PublicationResponse crearPublicacion(PublicacionDTO publicacionDTO) throws EntityNotFoundException;
    PublicationResponse editarPublicacion(Long id, PublicationEditRequest publicationEdit);
    void bajaLogica(Long id);
    void verPubliVisitante(Long id) throws EntityNotFoundException;
    List<PublicationResponse> traerUltimasTres() throws Exception;
    PublicationResponse addImage(AddImageToPublication ids);

}
