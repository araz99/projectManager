package dev.araz.mapper;

import dev.araz.dto.ProjectRespDTO;
import dev.araz.entity.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ProjectRespMapper implements Mapper<ProjectRespDTO, Project> {

    private final ListTaskRespMapper taskMapper;
    private final UserProjectRespMapper userMapper;

    @Override
    public Project toEntity(ProjectRespDTO projectRespDTO) {
        return null;
    }

    @Override
    public ProjectRespDTO toDTO(Project project) {
        return new ProjectRespDTO(
                project.getId(),
                project.getProjectName(),
                project.getKey(),
                project.getProjectType().getProjectTypeName(),
                project.getLead().getUsername(),
                project.getTasks().stream().map(taskMapper::toDTO).collect(Collectors.toSet()),
                project.getEmployees().stream().map(userMapper::toDTO).collect(Collectors.toSet()),
                project.getCreatedDate(),
                project.getLastModified(),
                project.getDescription()
        );
    }
}