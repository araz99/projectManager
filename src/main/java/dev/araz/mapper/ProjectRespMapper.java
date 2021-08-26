package dev.araz.mapper;

import dev.araz.dto.ProjectRespDTO;
import dev.araz.entity.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ProjectRespMapper implements MapperToDTO<ProjectRespDTO, Project> {

    @Override
    public ProjectRespDTO toDTO(Project project) {
        return new ProjectRespDTO(
                project.getId(),
                project.getProjectName(),
                project.getKey(),
                project.getProjectType().getProjectTypeName(),
                project.getLead().getUsername(),
                project.getTasks().size(),
                project.getEmployees().size(),
                project.getCreatedDate(),
                project.getLastModified(),
                project.getDescription()
        );
    }
}