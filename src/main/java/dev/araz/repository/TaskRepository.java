package dev.araz.repository;

import dev.araz.entity.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface TaskRepository
        extends PagingAndSortingRepository<Task, Long>,
        JpaSpecificationExecutor<Task> {
    List<Task> findAll(Long projectId, Pageable pageable);
}