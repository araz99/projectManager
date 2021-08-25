package dev.araz.mapper;

import dev.araz.dto.ProjectReqDTOForCreate;
import dev.araz.entity.Project;
import dev.araz.service.ProjectTypeService;
import dev.araz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Collections;

@Component
@RequiredArgsConstructor
public class ProjectReqMapperForCreate implements MapperToEntity<ProjectReqDTOForCreate, Project> {

    private final ProjectTypeService projectTypeService;
    private final UserService userService;

    @Override
    public Project toEntity(ProjectReqDTOForCreate dto) {
        return new Project(
                dto.getProjectName(),
                dto.getKey(),
                projectTypeService.getProjectByName(dto.getProjectType()),
                userService.getUserByName(dto.getLead()),
                Collections.emptySet(),
                Collections.emptySet(),
                new Date(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                dto.getDescription()
                );
    }
}