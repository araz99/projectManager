package dev.araz.mapper;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.araz.dto.ProjectRespDTO;
import dev.araz.entity.Project;
import dev.araz.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectRespMapper implements Mapper<ProjectRespDTO, Project> {

    private final ObjectMapper mapper;

    @Override
    public Project toEntity(ProjectRespDTO projectRespDTO) {
        return new Project();
    }

    @Override
    public ProjectRespDTO toDTO(Project project) {
//        ProjectRespDTO projectRespDTO = mapper.convertValue(project, ProjectRespDTO.class);
//        projectRespDTO.setProjectType(project.getProjectType().getProjectTypeName());
//        projectRespDTO.setLead(project.getLead().getUsername());
//        projectRespDTO.setQuantityTasks(project.getTasks().size());
//        projectRespDTO.setQuantityUsers(project.getUsers().size());
        return new ProjectRespDTO();
    }
}