package dev.araz.repository;

import dev.araz.entity.Project;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.sql.Timestamp;

@Repository
public interface ProjectRepository
        extends PagingAndSortingRepository<Project, Long>,
        JpaSpecificationExecutor<Project> {

    @Modifying
    @Query(value = "update project as pr set project_name = ?1, key = ?2, project_type_id = ?3, lead_id = ?4, description = ?5 where pr.id = ?6", nativeQuery = true)
    @Transactional
    void update(String name, String key, Long type, Long lead, String description, Long id);

    @Transactional
    @Modifying
    @Query(value = "update project as pr set last_modified = ?1 where pr.id = ?2", nativeQuery = true)
    void updateLastModified(Timestamp lastModified, Long id);
}