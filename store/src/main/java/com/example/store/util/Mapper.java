package com.example.store.util;
import org.modelmapper.ModelMapper;

public class Mapper {
    public static <S,D> D parseObject(S source, Class<D> Destination ){   
        ModelMapper modelMapper = new ModelMapper();
        D destionation = modelMapper.map(source,Destination);
        return destionation;
    }
}