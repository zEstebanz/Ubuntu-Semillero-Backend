package com.semillero.ubuntu.ChatBot.mappers;

import org.modelmapper.ModelMapper;

import java.util.List;
import java.util.stream.Collectors;

public class MapperUtil {
    private static final ModelMapper modelMapper = new ModelMapper();

    public static <T, U> U mapToDto(T entity, Class<U> dtoClass) {

        return modelMapper.map(entity, dtoClass);
    }

    public static <T, U> List<U> toDTOList(List<T> entityList, Class<U> dtoClass) {
        return entityList.stream().map(entity -> mapToDto(entity, dtoClass)).collect(Collectors.toList());}
}
