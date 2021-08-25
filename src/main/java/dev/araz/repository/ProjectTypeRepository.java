package dev.araz.repository;

import dev.araz.entity.ProjectType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProjectTypeRepository extends JpaRepository<ProjectType, Long> {

    Optional<ProjectType> findByProjectTypeName(String typeName);
}