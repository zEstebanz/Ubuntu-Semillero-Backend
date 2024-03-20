package com.semillero.ubuntu.Services.impl;

import com.semillero.ubuntu.DTOs.CalculoInversionDTO;
import com.semillero.ubuntu.DTOs.InversionDTO;
import com.semillero.ubuntu.DTOs.RecibirInversionDTO;
import com.semillero.ubuntu.Entities.Inversion;
import com.semillero.ubuntu.Repositories.InversionRepository;
import com.semillero.ubuntu.Repositories.UsuarioRepository;
import com.semillero.ubuntu.Services.InversionService;
import com.semillero.ubuntu.Utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InversionServiceImpl implements InversionService {
    @Autowired
    private InversionRepository inversionRepository;

    //Repositorio Microemprendimiento

    /**
     * Funcion para calcular la inversion
     * <p>
     * Utiliza como parámetros los datos del recibirInversionDTO incluidos el id del microemprendimiento que se desea invertir
     * <p>
     * Devuelve un DTO que no se persiste en la base de datos, por si el usuario desea cancelar.
     * <p>
     * Rol: Usuario Inversor
     * **/
    @Override
    public CalculoInversionDTO calcularInversion(RecibirInversionDTO recibirInversionDTO) throws Exception {
        try {
            //Buscar por id el microemprendimiento
            //Que el monto aportado se encuentre entre dos valores, ver si esos valores son constantes o cambian segun
            //el microemprendimiento
            //El factor de riesgo lo vamos a colocar entre 1.1 y 2, seccionando en tres partes para diferenciar
            //los niveles de factor.
            //Tasa de retorno parece ser un valor especifico de cada emprendimiento
            //Ver si las cuotas son un valor fijo o es una variable que tiene un tope, por ej: hasta 24 cuotas
            //Retorno Esperado = (montoAporte * Tasa de Retorno * Factor de Riesgo)
            //
            //Ganancia Total (Retorno Esperado - montoAporte - costosGestion)
            //Descripcion puede ser en el front o aca en el back
            //Asignar si se quiere devolver en el DTO el nombre del Microemprendimiento
            //Retornar mapeado el CalculoInversionDTO
            return null;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
/**
 * Funcion que guarda los datos de la inversion del usuario
 * **/
    @Override
    public void guardarInversion(CalculoInversionDTO calculoInversionDTO) throws Exception {
        try {

        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    /**
     * Funcion que busca y trae todas las inversiones creadas
     *<p>
     * Rol: SUPER ADMIN
     * **/
    @Override
    public List<InversionDTO> getAll() throws Exception {
        try {
        List<Inversion> inversiones = inversionRepository.findAll();
        return MapperUtil.toDTOList(inversiones, InversionDTO.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

/**
 * Función que devuelve todas las inversiones asociadas a un usuario
 * <p>
 * (Si se desea se puede complejizar la query para buscar también por nombre de Micro,
 * pero es mejor dejar eso para los filtros del front)
 * <p>
 * Rol: Usuario Inversor
 * **/
    @Override
    public List<InversionDTO> buscarPorUsuarioId(Long id) throws Exception {
        try {
        List<Inversion> inversiones = inversionRepository.buscarPorUsuarioId(id);
        return MapperUtil.toDTOList(inversiones, InversionDTO.class);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


}
