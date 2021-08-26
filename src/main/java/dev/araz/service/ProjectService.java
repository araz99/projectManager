package dev.araz.service;

import dev.araz.dto.ListProjectsRespDTO;
import dev.araz.dto.ProjectReqDTOForCreate;
import dev.araz.dto.ProjectRespDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public interface ProjectService {
    List<ListProjectsRespDTO> getProjects(Integer pageNumber, Integer pageSize, String sortByParam, String sortType);

    ResponseEntity<ProjectRespDTO> getProject(Long id);

    ResponseEntity<ProjectReqDTOForCreate> addProject(ProjectReqDTOForCreate projectDTO);

    ResponseEntity<ProjectRespDTO> updateProject(Long id, ProjectReqDTOForCreate dto);

    List<ProjectRespDTO> search(String projectName, String projectKey, String projectType, String projectLead, Date createdDate, Integer pageNumber, Integer pageSize, String sortByParam, String sortType);
}