package dev.araz.mapper;

import org.springframework.stereotype.Component;

@Component
public interface MapperToDTO<D, E> {
    D toDTO(E e);
}