package dev.araz.controller;

import dev.araz.dto.ProjectRespDTO;
import dev.araz.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import java.util.List;

@RestController
@RequestMapping("/projects")
@RequiredArgsConstructor
@Validated
public class ProjectController {

    private final ProjectService projectService;

    @Value("${forPagination.pageSize}")
    private Integer defaultPageSize;

    @GetMapping
    public List<ProjectRespDTO> allProjects(@RequestParam(required = false, defaultValue = "0") Integer pageNumber,
                                            @RequestParam(required = false) @Max(value = 15) Integer pageSize,
                                            @RequestParam(required = false, defaultValue = "projectName") String sortByParam,
                                            @RequestParam(required = false, defaultValue = "asc") String sortType) {
        if (pageSize == null)
            pageSize = defaultPageSize;
        return projectService.getProjects(pageNumber, pageSize, sortByParam, sortType);
    }

    // POST     /projects

    // PUT      /projects/{projectId}

    // DELETE   /projects/{projectID}
}