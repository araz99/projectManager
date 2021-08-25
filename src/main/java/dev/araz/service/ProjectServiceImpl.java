package dev.araz.service;

import dev.araz.dto.ListProjectsRespDTO;
import dev.araz.dto.ProjectReqDTOForCreate;
import dev.araz.dto.ProjectRespDTO;
import dev.araz.entity.Project;
import dev.araz.mapper.MapperToDTO;
import dev.araz.mapper.MapperToEntity;
import dev.araz.repository.ProjectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final MapperToDTO<ListProjectsRespDTO, Project> listProjectMapper;
    private final MapperToDTO<ProjectRespDTO, Project> projectMapperToDTO;
    private final MapperToEntity<ProjectReqDTOForCreate, Project> projectMapperToEntity;

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
    public ResponseEntity<ProjectRespDTO> getProject(Long id) {
        Optional<ProjectRespDTO> projectRespDTO = projectRepository.findById(id).map(projectMapperToDTO::toDTO).stream().findFirst();
        if (projectRespDTO.isPresent())
            return new ResponseEntity<>(projectRespDTO.get(), HttpStatus.OK);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, ("Project id " + id + "  is missing!"));
    }

    @Override
    public ResponseEntity<ProjectReqDTOForCreate> addProject(ProjectReqDTOForCreate projectDTO) {
        projectRepository.save(projectMapperToEntity.toEntity(projectDTO));
        return new ResponseEntity<>(projectDTO, HttpStatus.CREATED);
    }
}