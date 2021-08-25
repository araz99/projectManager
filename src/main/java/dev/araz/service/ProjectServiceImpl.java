package dev.araz.service;

import dev.araz.dto.ProjectRespDTO;
import dev.araz.entity.Project;
import dev.araz.mapper.Mapper;
import dev.araz.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final Mapper<ProjectRespDTO, Project> projectMapper;

    @Override
    public Page<Project> getProjects(Integer pageNumber, Integer pageSize, String sortByParam, String sortType) {
        PageRequest pageRequest;
        if (sortType.equals("desc"))
            pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortByParam).descending().and(Sort.by(sortByParam)));
        else
            pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortByParam).ascending());

        return projectRepository.findAll(pageRequest);
    }
}