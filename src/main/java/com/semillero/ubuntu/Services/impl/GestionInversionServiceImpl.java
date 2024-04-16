package com.semillero.ubuntu.Services.impl;

import com.semillero.ubuntu.DTOs.CalculoInversionDTO;
import com.semillero.ubuntu.DTOs.GestionInversionDTO;
import com.semillero.ubuntu.DTOs.RecibirInversionDTO;
import com.semillero.ubuntu.Entities.GestionInversion;
import com.semillero.ubuntu.Entities.Microemprendimiento;
import com.semillero.ubuntu.Enums.NivelRiesgo;
import com.semillero.ubuntu.Repositories.GestionInversionRepository;
import com.semillero.ubuntu.Repositories.MicroemprendimientoRepository;
import com.semillero.ubuntu.Services.GestionInversionService;
import com.semillero.ubuntu.Utils.Mapper;
import com.semillero.ubuntu.Utils.MapperUtil;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class GestionInversionServiceImpl implements GestionInversionService {
    @Autowired
    private GestionInversionRepository gestionInversionRepository;

    @Autowired
    private MicroemprendimientoRepository microemprendimientoRepository;

    /**
     * Funcion para calcular la inversion
     * <p>
     * Utiliza como parámetros los datos del recibirInversionDTO incluidos el id del microemprendimiento que se desea invertir
     * <p>
     * Devuelve un DTO que no se persiste en la base de datos, solo sirve como calculadora.
     * <p>
     * Rol: VISITANTE (No se implementa un usuario inversor todavia)
     * **/
    @Override
    public CalculoInversionDTO calcularInversion(RecibirInversionDTO recibirInversionDTO) throws Exception {
        //Puedo usar un try catch si es necesario
            //Verifico si existen tanto la gestion como el microemprendimiento
            //La busqueda se realiza con el idMicro, exigiendo que si existe el Gestionador de Inversiones, este debe estar asignado al Micro correspondiente
            GestionInversion gestion = gestionInversionRepository.buscarPorMicroId(recibirInversionDTO.getIdMicro())
                    .orElseThrow( () -> new EntityNotFoundException("Gestion no encontrada con id de Micro: " + recibirInversionDTO.getIdMicro()));
            Microemprendimiento micro = microemprendimientoRepository.findById(recibirInversionDTO.getIdMicro())
                    .orElseThrow( () -> new EntityNotFoundException("No se encontro el emprendimiento con id: " + recibirInversionDTO.getIdMicro()) );

            //Verifico si el monto aportado se encuentra entre el maximo y el minimo
            double montoAporte = recibirInversionDTO.getMontoAporte();
            if (montoAporte <= gestion.getMin() || montoAporte >= gestion.getMax()) {
                throw new IllegalArgumentException("Se debe ingresar un valor entre " + gestion.getMin() + " y " +  gestion.getMax());
            }

            //El tema de como calcular o recibir la cantidad de cuotas puede cambiar, por ahora solo recibe las cuotas que el usuario ingresa
            //Puede ser que haya que mapearlas tambien en un array
            Integer cuotas = recibirInversionDTO.getCuotas();
            if (cuotas <= 0) {
                throw new IllegalArgumentException("Se deben ingresar un numero de cuotas");
            }

            //Mapeo del factor de riesgo, por ahora esta hardcodeado
            Map<NivelRiesgo, Double> factoresRiesgo = Map.of(
                NivelRiesgo.BAJO, 1.2,
                NivelRiesgo.MEDIO, 1.5,
                NivelRiesgo.ALTO, 1.8
            );
            double factorRiesgo = factoresRiesgo.getOrDefault(gestion.getNivelRiesgo(), 1.0);

            double totalAporte = montoAporte + gestion.getCostosGestion();
            double montoCuota = totalAporte / recibirInversionDTO.getCuotas();
            double retornoEsperado = montoAporte * gestion.getTasaRetorno() * factorRiesgo;
            double gananciaTotal = retornoEsperado - totalAporte;

            //Mapeo de Valores manual
            return CalculoInversionDTO.builder()
                    .montoAportado(recibirInversionDTO.getMontoAporte())
                    .costosGestion(gestion.getCostosGestion())
                    .cuotas(recibirInversionDTO.getCuotas())
                    .totalAporte(totalAporte)
                    .retornoEsperado(retornoEsperado)
                    .montoCuota(montoCuota)
                    .gananciaTotal(gananciaTotal)
                    .factorRiesgo(factorRiesgo)
                    .tasaRetorno(gestion.getTasaRetorno())
                    .nivelRiesgo(gestion.getNivelRiesgo())
                    .notasAdicionales(gestion.getNotasAdicionales())

                    .nombreMicro(micro.getNombre())

                    .build();
    }
/**
 * Funcion que guarda la gestion de inversiones asignada a cierto microemprendimiento
 * <p>
 * Puede que haya que reinterpretar esta funcion (y la entidad en su totalidad)
 * <p>
 * Rol: ADMIN
 **/
    @Override
    public GestionInversionDTO crearGestion(GestionInversionDTO gestionInversionDTO) throws Exception {
            Microemprendimiento micro = microemprendimientoRepository.findById(gestionInversionDTO.getIdMicro())
                    .orElseThrow(() -> new EntityNotFoundException("No se encontro el emprendimiento con id: " + gestionInversionDTO.getIdMicro()));

            if (gestionInversionDTO.getMax() <= gestionInversionDTO.getMin()) {
                throw new IllegalArgumentException("El minimo debe ser menor al maximo");
            }

            GestionInversion gestionInversion = GestionInversion.builder()
                    .costosGestion(gestionInversionDTO.getCostosGestion())
                    .notasAdicionales(gestionInversionDTO.getNotasAdicionales())
                    //El tema de como calcular o recibir la cantidad de cuotas puede cambiar, por ahora solo recibe las cuotas que el usuario ingresa
                    //Puede ser que haya que mapearlas tambien en un array
                    .cuotas(gestionInversionDTO.getCuotas())
                    .max(gestionInversionDTO.getMax())
                    .min(gestionInversionDTO.getMin())
                    .tasaRetorno(gestionInversionDTO.getTasaRetorno())
                    .nivelRiesgo(gestionInversionDTO.getNivelRiesgo())
                    .microemprendimiento(micro)
                    .inactivo(gestionInversionDTO.getInactivo())
                    .build();

            try {
                gestionInversionRepository.save(gestionInversion);
            } catch (Exception e) {
                throw new RuntimeException("Error al guardar la gestión del microemprendimiento " + gestionInversionDTO.getIdMicro(), e);
            }
            return Mapper.respuestaGestionInversion(gestionInversion, gestionInversionDTO.getIdMicro());
    }

    /**
     *  Funcion para editar las gestiones
     *  <p>
     *  Rol: ADMIN
     * **/
    @Override
    public GestionInversionDTO editarGestion(GestionInversionDTO gestionInversionDTO) throws Exception {
        try {
            //Verifico si existen tanto la gestion como el microemprendimiento y si coinciden
            //La busqueda se realiza con el idMicro, exigiendo que si existe el Gestionador de Inversiones, este debe estar asignado al Micro correspondiente
            GestionInversion gestion = gestionInversionRepository.buscarPorMicroId(gestionInversionDTO.getIdMicro())
                    .orElseThrow( () -> new EntityNotFoundException("Gestion no encontrada con id de Micro: " + gestionInversionDTO.getIdMicro()));
            Microemprendimiento micro = microemprendimientoRepository.findById(gestionInversionDTO.getIdMicro())
                    .orElseThrow( () -> new EntityNotFoundException("No se encontro el emprendimiento con id: " + gestionInversionDTO.getIdMicro()));

            if (!micro.getId().equals(gestionInversionDTO.getIdMicro())) {
                throw new IllegalArgumentException("El id de microemprendimiento no coincide con la gestión a editar");
            }

            if (gestionInversionDTO.getMax() <= gestionInversionDTO.getMin()) {
                throw new IllegalArgumentException("El minimo debe ser menor al maximo");
            }

            gestion.setCostosGestion(gestionInversionDTO.getCostosGestion());
            gestion.setMax(gestionInversionDTO.getMax());
            gestion.setMin(gestionInversionDTO.getMin());
            //El tema de como calcular o recibir la cantidad de cuotas puede cambiar, por ahora solo recibe las cuotas que el usuario ingresa
            //Puede ser que haya que mapearlas tambien en un array
            gestion.setCuotas(gestion.getCuotas());
            gestion.setTasaRetorno(gestionInversionDTO.getTasaRetorno());
            gestion.setNivelRiesgo(gestionInversionDTO.getNivelRiesgo());
            gestion.setInactivo(gestionInversionDTO.getInactivo());
            gestion.setNotasAdicionales(gestionInversionDTO.getNotasAdicionales());
            try {
                gestionInversionRepository.save(gestion);
            } catch (Exception e) {
                throw new RuntimeException("Error al guardar la gestión del microemprendimiento " + gestionInversionDTO.getIdMicro(), e);
            }
            return Mapper.respuestaGestionInversion(gestion, gestionInversionDTO.getIdMicro());
        } catch (EntityNotFoundException | IllegalArgumentException e) { //Atrapo los errores surgidos en los argumentos
            throw e;
        }
       catch (Exception e) { //Atrapo cualquier error surgido en Runtime
            throw new Exception("Error al editar la gestion" + e.getMessage());
       }
    }

    /**
     * Funcion que busca y trae todas las gestiones creadas (Error de Mapeo, arreglar)
     *<p>
     * Rol: SUPER ADMIN
     * **/
    @Override
    public List<GestionInversionDTO> getAll() throws Exception {
        try {
        List<GestionInversion> inversiones = gestionInversionRepository.findAll();
        return MapperUtil.toDTOList(inversiones, GestionInversionDTO.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
 /**
  * Funcion que se encarga de activar o desactivar el Gestionador de Inversiones asignado al Microemprendimiento
  * <p>
  *  Rol: ADMIN
  **/
    @Override
    public void logicaGestion(Long idMicro) throws Exception {
        try {
            //La busqueda se realiza con el idMicro, exigiendo que si existe el Gestionador de Inversiones, este debe estar asignado al Micro correspondiente
            GestionInversion gestion = gestionInversionRepository.buscarPorMicroId(idMicro)
                    .orElseThrow( () -> new EntityNotFoundException("Gestion no encontrada con id de Micro: " + idMicro));
            //Asignamos el valor opuesto de la var Activa
            gestion.setInactivo(!gestion.getInactivo());
            gestionInversionRepository.save(gestion);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Funcion que busca y trae la gestion asociada a la inversion (En revision)
     *<p>
     * Rol: ADMIN
     * <p>
     *  Notas: Es necesario que la logica tambien contemple la posibilidad de que no exista
     *  la gestion asi el admin puede crearla, entonces hay que ver el manejo de errores en el front y evitar un exception
     *  que no permita ver la gestion
     * **/
    public GestionInversionDTO getInversion(Long idMicro) throws Exception {
        try {
            //La busqueda se realiza con el idMicro, exigiendo que si existe el Gestionador de Inversiones, este debe estar asignado al Micro correspondiente
            GestionInversion gestion = gestionInversionRepository.buscarPorMicroId(idMicro)
                    .orElseThrow( () -> new EntityNotFoundException("Gestion no encontrada con id de Micro: " + idMicro + ". Desea crear una?"));
            return Mapper.respuestaGestionInversion(gestion, idMicro);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
