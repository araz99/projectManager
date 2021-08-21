package dev.araz.service;

import dev.araz.dto.ProjectRespDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProjectService {
    List<ProjectRespDTO> getProjects(Integer pageNumber, Integer pageSize, String sortByParam, String sortType);
}