package dev.araz.controller;

import dev.araz.dto.ListProjectsRespDTO;
import dev.araz.dto.ProjectReqDTO;
import dev.araz.dto.ProjectRespDTO;
import dev.araz.service.ProjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Date;
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
    public List<ListProjectsRespDTO> getAllProjects(
            @RequestParam(required = false, defaultValue = "0")
            @Min(value = 0L, message = "{page.number}") Integer pageNumber,

            @RequestParam(required = false)
            @Min(value = 3L, message = "{page.size}") Integer pageSize,

            @RequestParam(required = false, defaultValue = "id")
            @NotBlank @Pattern(regexp = "(?i)(\\s*id\\s*|\\s*projectName\\s*|\\s*key\\s*|\\s*projectType\\s*|\\s*lead\\s*|\\s*createdDate\\s*|\\s*lastModified\\s*)", message = "{sort.byParam}") String sortByParam,

            @RequestParam(required = false, defaultValue = "asc")
            @NotBlank @Pattern(regexp = "(?i)(\\s*asc\\s*|\\s*desc\\s*)", message = "{sort.type}") String sortType) {
        if (pageSize == null)
            pageSize = defaultPageSize;
        return projectService.getProjects(pageNumber, pageSize, sortByParam, sortType);
    }

    @GetMapping("{id}")
    public ResponseEntity<ProjectRespDTO> getProject(@PathVariable @Min(value = 1L, message = "{path.variable.id}") Long id) {
        return projectService.getProject(id);
    }

    @PostMapping
    public ResponseEntity<ProjectReqDTO> addProject(
            @Valid @RequestBody ProjectReqDTO projectDTO) {
        return projectService.addProject(projectDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity<ProjectRespDTO> changeProject(
            @PathVariable @Min(value = 1L, message = "{path.variable.id}") Long id,
            @Valid @RequestBody ProjectReqDTO projectDTO) {
        return projectService.updateProject(id, projectDTO);
    }

    // DELETE   /projects/{projectID}

    @GetMapping("/search")
    public List<ProjectRespDTO> filter(
            @RequestParam(required = false)
            @Pattern(regexp = ".*(\\S\\S\\S|\\S\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}") String name,

            @RequestParam(required = false)
            @Pattern(regexp = ".*(\\S\\S\\S|\\S\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}") String key,

            @RequestParam(required = false)
            @Pattern(regexp = ".*(\\S\\S\\S|\\S\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}") String type,

            @RequestParam(required = false)
            @Pattern(regexp = ".*(\\S\\S\\S|\\S\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}") String lead,

            @RequestParam(required = false)
            @DateTimeFormat(pattern = "yyyy/mm/dd") Date createdDate,

            // Parameters for pagination and sorting
            @RequestParam(required = false, defaultValue = "0")
            @Min(value = 0L, message = "{page.number}") Integer pageNumber,

            @RequestParam(required = false)
            @Min(value = 3L, message = "{page.size}") Integer pageSize,

            @RequestParam(required = false, defaultValue = "id")
            @NotBlank @Pattern(regexp = "(\\s*id\\s*|\\s+projectName\\s*|\\s*key\\s*|\\s*projectType\\s*|\\s*lead\\s*|\\s*createdDate\\s*|\\s*lastModified\\s*)", message = "{sort.byParam}") String sortByParam,

            @RequestParam(required = false, defaultValue = "asc")
            @NotBlank @Pattern(regexp = "(\\s*asc\\s*|\\s*desc\\s*)", message = "{sort.type}") String sortType
    ) {
        if (pageSize == null)
            pageSize = defaultPageSize;
        return projectService.search(name, key, type, lead, createdDate, pageNumber, pageSize, sortByParam, sortType);
    }
}