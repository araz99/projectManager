package dev.araz.repository;

import dev.araz.entity.Task;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository
        extends PagingAndSortingRepository<Task, Long>,
        JpaSpecificationExecutor<Task> {
    List<Task> findAll(Long projectId, Pageable pageable);

    Optional<Task> findById(Long projectId, Long id);


    @Modifying
    @Query(value =
            "update task as t set task_name = ?1, executor_id = ?2, issue_type_id = ?3, priority_id = ?4, status_id = ?5, description = ?6  where t.priority_id = ?7 and t.id = ?8",
            nativeQuery = true)
    @Transactional
    void update(String taskName, Long executor, Long type, Long priority, Long status, String description, Long projectId, Long id);
}