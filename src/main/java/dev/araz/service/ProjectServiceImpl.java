package dev.araz.service;

import dev.araz.dto.ListProjectsRespDTO;
import dev.araz.dto.ProjectRespDTO;
import dev.araz.entity.Project;
import dev.araz.mapper.Mapper;
import dev.araz.mapper.MapperToDTO;
import dev.araz.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final MapperToDTO<ListProjectsRespDTO, Project> listProjectMapper;
    private final Mapper<ProjectRespDTO, Project> projectMapper;

    @Override
    public List<ListProjectsRespDTO> getProjects(Integer pageNumber, Integer pageSize, String sortByParam, String sortType) {
        PageRequest pageRequest;
        if (sortType.equals("desc"))
            pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortByParam).descending());
        else
            pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortByParam).ascending());

        return projectRepository.findAll(pageRequest)
                .stream().map(listProjectMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<ProjectRespDTO> getProject(Long id) {
        return projectRepository.findById(id).map(projectMapper::toDTO).stream().findFirst();
    }
}