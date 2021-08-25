package dev.araz.service;

import dev.araz.entity.Project;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public interface ProjectService {
    Page<Project> getProjects(Integer pageNumber, Integer pageSize, String sortByParam, String sortType);
}