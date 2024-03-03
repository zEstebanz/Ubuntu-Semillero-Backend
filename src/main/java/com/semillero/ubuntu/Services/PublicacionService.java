package com.semillero.ubuntu.Services;

import com.semillero.ubuntu.Entities.Publicacion;
import com.semillero.ubuntu.Repositories.PublicacionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Optional;

@Service
public class PublicacionService {
    @Autowired
    private PublicacionRepository publicacionRepository;

    @Transactional
    public Publicacion save(Publicacion publicacionDTO) throws Exception { //Utilice un builder para asignarle la fecha mas facil
        try {
            Publicacion publi = Publicacion.builder()
                    .titulo(publicacionDTO.getTitulo())
                    .descripcion(publicacionDTO.getDescripcion())
                    .fechaCreacion(LocalDate.now())
                    .cantVistas(0)
                    .isDeleted(publicacionDTO.getIsDeleted()) //Va a ser cero siempre, de ultima borrar en el DTO y hardcodear el booleano cuando se crea
                    .build();
            publicacionRepository.save(publi);
            return publi;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public void update(Long id, Publicacion publicacionDTO) throws Exception {
        try {
            Optional<Publicacion> publicacionOptional = publicacionRepository.findById(id);
            Publicacion publicacion = publicacionOptional.get();
            publicacion.setTitulo(publicacionDTO.getTitulo());
            publicacion.setDescripcion(publicacionDTO.getDescripcion());
            publicacion.setIsDeleted(publicacionDTO.getIsDeleted());
            //Falta la edicion de imagenes
            publicacionRepository.save(publicacion);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public void bajaLogica(Long id, Publicacion publicacionDTO) throws Exception {   //Puede que tenga que cambiar el DTO
        try {
            Optional<Publicacion> publicacionOptional = publicacionRepository.findById(id);
            Publicacion publicacion = publicacionOptional.get();
            publicacion.setIsDeleted(publicacionDTO.getIsDeleted());
            publicacionRepository.save(publicacion);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Transactional
    public void verPublicacion(Long id) throws Exception {        //Esta funcion solo debe activarla el visitante en teoria
        try {
            Optional<Publicacion> publicacionOptional = publicacionRepository.findById(id);
            Publicacion publicacion = publicacionOptional.get();
            int sumaVista = publicacion.getCantVistas();
            sumaVista++;
            publicacion.setCantVistas(sumaVista);
            publicacionRepository.save(publicacion);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}

