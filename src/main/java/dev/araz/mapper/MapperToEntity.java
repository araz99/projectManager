package dev.araz.mapper;

import org.springframework.stereotype.Component;

@Component
public interface MapperToEntity<D, E> {
    E toEntity(D d);
}