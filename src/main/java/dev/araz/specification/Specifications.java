package dev.araz.specification;

import org.springframework.data.jpa.domain.Specification;

public class Specifications<E> {

    private Specifications() {}

    public static <E, F> Specification<E> has(String fieldName, F field) {
        return field == null ? emptySpecification()
                : (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(fieldName), field);
    }

    private static <E> Specification<E> emptySpecification() {
        return (root, criteriaQuery, criteriaBuilder) -> null;
    }
}