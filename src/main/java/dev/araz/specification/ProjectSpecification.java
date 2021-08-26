package dev.araz.specification;

import dev.araz.entity.Project;
import org.springframework.data.jpa.domain.Specification;

public class ProjectSpecification {

    public static <T> Specification<Project> has(String fieldName, T field) {
        return field == null ? emptySpecification()
                : (root, criteriaQuery, criteriaBuilder) -> criteriaBuilder.equal(root.get(fieldName), field);
    }

    private static Specification<Project> emptySpecification() {
        return (root, criteriaQuery, criteriaBuilder) -> null;
    }
}