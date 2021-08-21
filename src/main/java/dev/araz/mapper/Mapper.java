package dev.araz.mapper;

import org.springframework.stereotype.Component;

@Component
public interface Mapper<D, E> {
    E toEntity(D d);
    D toDTO(E e);
}