package dev.araz.repository;

import dev.araz.entity.TaskPriority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskPriorityRepository extends JpaRepository<TaskPriority, Long> {
    Optional<TaskPriority> findTaskPriorityByPriorityName(String name);
}