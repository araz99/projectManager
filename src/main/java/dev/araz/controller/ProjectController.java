package dev.araz.controller;

import dev.araz.dto.ListProjectsRespDTO;
import dev.araz.dto.ProjectReqDTOForCreate;
import dev.araz.dto.ProjectRespDTO;
import dev.araz.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
@Validated
public class ProjectController {

    private final ProjectService projectService;

    @Value("${forPagination.pageSize}")
    private Integer defaultPageSize;

    @GetMapping
    public List<ListProjectsRespDTO> allProjects(@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                                 @RequestParam(required = false) @Max(value = 15) Integer pageSize,
                                                 @RequestParam(required = false, defaultValue = "id") String sortByParam,
                                                 @RequestParam(required = false, defaultValue = "asc") String sortType) {
        if (pageSize == null)
            pageSize = defaultPageSize;
        return projectService.getProjects(pageNumber, pageSize, sortByParam, sortType);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProjectRespDTO> getProject(@PathVariable Long id) {
        return projectService.getProject(id);
    }

    // POST     /projects
    @PostMapping
    public ResponseEntity<ProjectReqDTOForCreate> addProject(@RequestBody ProjectReqDTOForCreate projectDTO) {
        return projectService.addProject(projectDTO);
    }

    // PUT      /projects/{projectId}

    // DELETE   /projects/{projectID}
}