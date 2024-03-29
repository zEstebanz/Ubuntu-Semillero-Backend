package com.semillero.ubuntu.Services.impl;

import com.semillero.ubuntu.DTOs.CalculoInversionDTO;
import com.semillero.ubuntu.DTOs.GestionInversionDTO;
import com.semillero.ubuntu.DTOs.RecibirInversionDTO;
import com.semillero.ubuntu.Entities.GestionInversion;
import com.semillero.ubuntu.Entities.Microemprendimiento;
import com.semillero.ubuntu.Repositories.GestionInversionRepository;
import com.semillero.ubuntu.Repositories.MicroemprendimientoRepository;
import com.semillero.ubuntu.Repositories.UsuarioRepository;
import com.semillero.ubuntu.Services.GestionInversionService;
import com.semillero.ubuntu.Utils.MapperUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GestionInversionServiceImpl implements GestionInversionService {
    @Autowired
    private GestionInversionRepository gestionInversionRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private MicroemprendimientoRepository microemprendimientoRepository;

    /**
     * Funcion para calcular la inversion
     * <p>
     * Utiliza como parámetros los datos del recibirInversionDTO incluidos el id del microemprendimiento que se desea invertir
     * <p>
     * Devuelve un DTO que no se persiste en la base de datos, solo sirve como calculadora por ahora.
     * <p>
     * Rol: Usuario Inversor
     * **/
    @Override
    public CalculoInversionDTO calcularInversion(RecibirInversionDTO recibirInversionDTO) throws Exception {
        try {
            Optional<GestionInversion> gestionInversion = gestionInversionRepository.buscarPorMicroId(recibirInversionDTO.getIdMicro());
            Optional<Microemprendimiento> microemprendimiento = microemprendimientoRepository.findById(recibirInversionDTO.getIdMicro());

            //Verifico si existen tanto la gestion como el microemprendimiento
            if (gestionInversion.isPresent() && microemprendimiento.isPresent()) {
                GestionInversion gestion = gestionInversion.get();
                Microemprendimiento micro = microemprendimiento.get();
                //Verifico si el monto aportado se encuentra entre el maximo y el minimo
                if (recibirInversionDTO.getMontoAporte() >= gestion.getMin() && recibirInversionDTO.getMontoAporte() <= gestion.getMax()) {
                    double totalAporte = recibirInversionDTO.getMontoAporte() + gestion.getCostosGestion();
                    //El tema de como calcular o recibir la cantidad de cuotas puede cambiar, por ahora solo recibe las cuotas que el usuario ingresa
                    double montoCuota = totalAporte / recibirInversionDTO.getCuotas();
                    double factorRiesgo = 1;
                    switch (gestion.getNivelRiesgo()) {
                        //Valores hardcodeados por ahora, si se desean editar hay que crear una variable en GestionInversion
                        case ALTO -> factorRiesgo = 1.8;
                        case BAJO -> factorRiesgo = 1.2;
                        case MEDIO -> factorRiesgo = 1.5;
                    }
                    double retornoEsperado = recibirInversionDTO.getMontoAporte() * gestion.getTasaRetorno() * factorRiesgo;
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

                            .descripcion(micro.getDescripcion())
                            .nombreMicro(micro.getNombre())

                            .build();
                }
            }
            //Puede que haya problemas con este null, revisar por las dudas, sino usar el EntityNotFound o borrar el try catch
            return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
/**
 * Funcion que guarda la gestion de inversiones asignada a cierto microemprendimiento
 * <p>
 * Puede que haya que reinterpretar esta funcion (y la entidad en su totalidad)
 * <p>
 * Rol: ADMIN
 *
 * @return
 **/
    @Override
    public GestionInversionDTO crearGestion(GestionInversionDTO gestionInversionDTO) throws Exception {
        try {
            Optional<Microemprendimiento> microemprendimiento = microemprendimientoRepository.findById(gestionInversionDTO.getIdMicro());
            if (microemprendimiento.isPresent()) {
                Microemprendimiento micro = microemprendimiento.get();

                GestionInversion gestionInversion = GestionInversion.builder()
                        .costosGestion(gestionInversionDTO.getCostosGestion())
                        //Elijo la descripcion del micro por ahora, si es necesario que gestion tenga una aparte cambiar
                        .descripcion(micro.getDescripcion())
                        //No se entiende realmente este campo
                        .cuotas(0)
                        .max(gestionInversionDTO.getMax())
                        .min(gestionInversionDTO.getMin())
                        .tasaRetorno(gestionInversionDTO.getTasaRetorno())
                        .nivelRiesgo(gestionInversionDTO.getNivelRiesgo())
                        .microemprendimiento(micro)
                        .Activo(gestionInversionDTO.getActivo())
                        .build();
                gestionInversionRepository.save(gestionInversion);
                return MapperUtil.mapToDto(gestionInversion, GestionInversionDTO.class);
            }
            //Puede que haya problemas con este null, revisar por las dudas, sino usar el EntityNotFound
            return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     *  Funcion para editar las gestiones
     *  <p>
     *  Rol: ADMIN
     * **/
    @Override
    public GestionInversionDTO editarGestion(Long id, GestionInversionDTO gestionInversionDTO) throws Exception {
        try {
            Optional<GestionInversion> gestionInversion = gestionInversionRepository.buscarPorMicroId(id);
            Optional<Microemprendimiento> microemprendimiento = microemprendimientoRepository.findById(gestionInversionDTO.getIdMicro());

            //Verifico si existen tanto la gestion como el microemprendimiento
            if (gestionInversion.isPresent() && microemprendimiento.isPresent()) {
                GestionInversion gestion = gestionInversion.get();
                gestion.setCostosGestion(gestion.getCostosGestion());
                gestion.setMax(gestionInversionDTO.getMax());
                gestion.setMin(gestionInversionDTO.getMin());
                gestion.setTasaRetorno(gestionInversionDTO.getTasaRetorno());
                gestion.setNivelRiesgo(gestionInversionDTO.getNivelRiesgo());
                gestion.setActivo(gestionInversionDTO.getActivo());
                gestionInversionRepository.save(gestion);
                return MapperUtil.mapToDto(gestion, GestionInversionDTO.class);
            }
            //Puede que haya problemas con este null, revisar por las dudas, sino usar el EntityNotFound
            return null;
        }
       catch (Exception e) {
            throw new Exception(e.getMessage());
       }
    }

    /**
     * Funcion que busca y trae todas las gestiones creadas
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

}
