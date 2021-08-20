package dev.araz.mapper;

public interface Mapper<D, E> {
    E toEntity(D d);
    D toDTO(E e);
}