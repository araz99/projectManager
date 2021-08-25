package dev.araz.service;

import dev.araz.dto.ListProjectsRespDTO;
import dev.araz.dto.ProjectReqDTOForCreate;
import dev.araz.dto.ProjectRespDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
    List<ListProjectsRespDTO> getProjects(Integer pageNumber, Integer pageSize, String sortByParam, String sortType);

    ResponseEntity<ProjectRespDTO> getProject(Long id);

    ResponseEntity<ProjectReqDTOForCreate> addProject(ProjectReqDTOForCreate projectDTO);

    ResponseEntity<ProjectRespDTO> updateProject(Long id, ProjectReqDTOForCreate dto);
}