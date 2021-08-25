package dev.araz.service;

import dev.araz.dto.ListProjectsRespDTO;
import dev.araz.dto.ProjectRespDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProjectService {
    List<ListProjectsRespDTO> getProjects(Integer pageNumber, Integer pageSize, String sortByParam, String sortType);

    Optional<ProjectRespDTO> getProject(Long id);
}