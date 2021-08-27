package dev.araz.repository;

import dev.araz.entity.IssueType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IssueTypeRepository extends JpaRepository<IssueType, Long> {

    Optional<IssueType> findByIssueTypeName(String type);
}