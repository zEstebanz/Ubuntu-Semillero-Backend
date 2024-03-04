package com.semillero.ubuntu.Utils;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MapperUtil {
    private static final ModelMapper modelMapper = new ModelMapper();
    /**
     * Maps an entity to a DTO of the specified class.
     *
     * @param entity   The entity object to be mapped.
     * @param dtoClass The class of the DTO.
     * @param <T>      The type of the entity.
     * @param <U>      The type of the DTO.
     * @return The mapped DTO object.
     */
    public static <T, U> U mapToDto(T entity, Class<U> dtoClass) {
        return modelMapper.map(entity, dtoClass);
    }
    /**
     * Maps a list of entities to a list of DTOs of the specified class.
     *
     * @param entityList The list of entity objects to be mapped.
     * @param dtoClass   The class of the DTO.
     * @param <T>        The type of the entity.
     * @param <U>        The type of the DTO.
     * @return The list of mapped DTO objects.
     */
    public static <T, U> List<U> toDTOList(List<T> entityList, Class<U> dtoClass) {
        return entityList.stream()
                .map(entity -> mapToDto(entity, dtoClass))
                .collect(Collectors.toList());
    }
}