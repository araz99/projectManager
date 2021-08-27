package dev.araz.service;

import dev.araz.dto.ListProjectsRespDTO;
import dev.araz.dto.ProjectReqDTOForCreate;
import dev.araz.dto.ProjectRespDTO;
import dev.araz.entity.Project;
import dev.araz.entity.ProjectType;
import dev.araz.entity.User;
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

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static dev.araz.specification.ProjectSpecification.*;

@Component
@RequiredArgsConstructor
public class ProjectServiceImpl implements ProjectService {

    private final ProjectRepository projectRepository;
    private final MapperToDTO<ListProjectsRespDTO, Project> listProjectMapper;
    private final MapperToDTO<ProjectRespDTO, Project> projectMapperToDTO;
    private final MapperToEntity<ProjectReqDTOForCreate, Project> projectMapperToEntity;
    private final ProjectTypeService projectTypeService;
    private final UserService userService;

    // Project field names for Spring Specification
    private static final String Project_name = "projectName";
    private static final String Project_key = "key";
    private static final String Project_type = "projectType";
    private static final String Project_lead = "lead";
    private static final String Project_tasks = "tasks";
    private static final String Project_employees = "employees";
    private static final String Project_createdDate = "createdDate";

    @Override
    public List<ListProjectsRespDTO> getProjects(Integer pageNumber, Integer pageSize, String sortByParam, String sortType) {
        PageRequest pageRequest = getPageRequest(pageNumber, pageSize, sortByParam, sortType);
        return projectRepository.findAll(pageRequest)
                .stream().map(listProjectMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<ProjectRespDTO> getProject(Long id) {
        Optional<ProjectRespDTO> projectRespDTO = projectRepository.findById(id).map(projectMapperToDTO::toDTO).stream().findFirst();
        if (projectRespDTO.isPresent())
            return new ResponseEntity<>(projectRespDTO.get(), HttpStatus.OK);
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Project id = " + id + "  is not exists!");
    }

    @Override
    public ResponseEntity<ProjectReqDTOForCreate> addProject(ProjectReqDTOForCreate projectDTO) {
        projectRepository.save(projectMapperToEntity.toEntity(projectDTO));
        return new ResponseEntity<>(projectDTO, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<ProjectRespDTO> updateProject(Long id, ProjectReqDTOForCreate dto) {
        Optional<Project> optionalProject = projectRepository.findById(id);
        if (optionalProject.isEmpty())
            addProject(dto);
        Project project = projectMapperToEntity.toEntity(dto);
        project.setId(id);
        project.setLastModified(new Timestamp(System.currentTimeMillis()));
        projectRepository.updateLastModified(new Timestamp(System.currentTimeMillis()), id);
        projectRepository.update(project.getProjectName(), project.getKey(), project.getProjectType().getId(), project.getLead().getId(), project.getDescription(), id);
        return new ResponseEntity<>(projectMapperToDTO.toDTO(project), HttpStatus.OK);
    }

    @Override
    public List<ProjectRespDTO> search(String projectName, String projectKey, String projectType, String projectLead, Date createdDate, Integer pageNumber, Integer pageSize, String sortByParam, String sortType) {
        ProjectType PType = projectType == null ? null : projectTypeService.getProjectByName(projectType);
        User user = projectLead == null ? null : userService.getUserByName(projectLead);
        PageRequest pageRequest = getPageRequest(pageNumber, pageSize, sortByParam, sortType);
        return projectRepository.findAll(
                has(Project_name, projectName)
                        .and(has(Project_key, projectKey))
                        .and(has(Project_type, PType))
                        .and(has(Project_lead, user))
                        .and(has(Project_createdDate, createdDate)), pageRequest)
                .stream().map(projectMapperToDTO::toDTO).collect(Collectors.toList());
    }

    private PageRequest getPageRequest(Integer pageNumber, Integer pageSize, String sortByParam, String sortType) {
        PageRequest pageRequest;
        if (sortType.equals("desc"))
            pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortByParam).descending());
        else
            pageRequest = PageRequest.of(pageNumber, pageSize, Sort.by(sortByParam).ascending());
        return pageRequest;
    }
}