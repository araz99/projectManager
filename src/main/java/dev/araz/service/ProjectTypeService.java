package dev.araz.service;

import dev.araz.entity.ProjectType;
import dev.araz.exception.NotProjectTypeException;
import dev.araz.repository.ProjectTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProjectTypeService {

    private final ProjectTypeRepository repository;

    public ProjectType getProjectByName(String typeName) {
        Optional<ProjectType> projectType = repository.findByProjectTypeName(typeName);
        if (projectType.isPresent())
            return projectType.get();
        throw new NotProjectTypeException("Project type with the name " + typeName + " does not exist!");
    }
}