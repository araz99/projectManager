package dev.araz.mapper;

import dev.araz.dto.ListProjectsRespDTO;
import dev.araz.entity.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ListProjectsRespMapper implements MapperToDTO<ListProjectsRespDTO, Project> {

    @Override
    public ListProjectsRespDTO toDTO(Project project) {
        return new ListProjectsRespDTO(
                project.getId(),
                project.getProjectName(),
                project.getKey(),
                project.getProjectType().getProjectTypeName(),
                project.getLead().getUsername(),
                project.getEmployees().size(),
                project.getTasks().size()
        );
    }
}