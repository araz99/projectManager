package dev.araz.controller;

import dev.araz.dto.ListTaskRespDTO;
import dev.araz.dto.TaskReqDTO;
import dev.araz.dto.TaskReqDTOForUpdate;
import dev.araz.dto.TaskRespDTO;
import dev.araz.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
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
@RequestMapping("/projects/{projectId}/tasks")
@RequiredArgsConstructor
@Validated
public class TaskController {

    private final TaskService taskService;

    @Value("${forPagination.pageSize}")
    private Integer defaultPageSize;

    @GetMapping
    public List<ListTaskRespDTO> getAllTasks(
            @PathVariable @Min(value = 1L, message = "{path.variable.id}") Long projectId,

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
        return taskService.getTasks(projectId, pageNumber, pageSize, sortByParam, sortType);
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskRespDTO> getProject(
            @PathVariable @Min(value = 1L, message = "{path.variable.id}") Long projectId,
            @PathVariable @Min(value = 1L, message = "{path.variable.id}") Long id) {
        return taskService.getTask(projectId, id);
    }

    @PostMapping
    public ResponseEntity<TaskRespDTO> addTask(
            @PathVariable @Min(value = 1L, message = "{path.variable.id}") Long projectId,
            @Valid @RequestBody TaskReqDTO taskDTO
    ) {
        return taskService.addTask(projectId, taskDTO);
    }

    @PutMapping("{id}")
    public ResponseEntity changeTask(
            @PathVariable @Min(value = 1L, message = "{path.variable.id}") Long projectId,
            @PathVariable @Min(value = 1L, message = "{path.variable.id}") Long id,
            @RequestBody TaskReqDTOForUpdate taskDTO
    ) {
        return taskService.updateTask(projectId, id, taskDTO);
    }

    @GetMapping("/search")
    public List<TaskRespDTO> filter(
            @PathVariable @Min(value = 1L, message = "{path.variable.id}") Long projectId,

            @Pattern(regexp = ".*(\\S\\S\\S|\\S\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}")
            @RequestParam(required = false) String name,

            @Pattern(regexp = ".*(\\S\\S\\S|\\S\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}")
            @RequestParam(required = false) String author,

            @Pattern(regexp = ".*(\\S\\S\\S|\\S\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}")
            @RequestParam(required = false) String executor,

            @Pattern(regexp = ".*(\\S\\S\\S|\\S\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}")
            @RequestParam(required = false) String type,

            @Pattern(regexp = ".*(\\S\\S\\S|\\S\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}")
            @RequestParam(required = false) String priority,

            @Pattern(regexp = ".*(\\S\\S|\\S\\S+).*", message = "{pattern.non-whitespace.characters.three}")
            @RequestParam(required = false) String status,

            @DateTimeFormat(pattern = "yyyy/mm/dd")
            @RequestParam(required = false) Date createdDate,

            // parameters for pagination and sorting
            @RequestParam(required = false, defaultValue = "0")
            @Min(value = 0L, message = "{page.number}") Integer pageNumber,

            @RequestParam(required = false)
            @Min(value = 3L, message = "{page.size}") Integer pageSize,

            @RequestParam(required = false, defaultValue = "id")
            @NotBlank @Pattern(regexp = "(?i)(\\s*id\\s*|\\s*projectName\\s*|\\s*key\\s*|\\s*projectType\\s*|\\s*lead\\s*|\\s*createdDate\\s*|\\s*lastModified\\s*)", message = "{sort.byParam}") String sortByParam,

            @RequestParam(required = false, defaultValue = "asc")
            @NotBlank @Pattern(regexp = "(?i)(\\s*asc\\s*|\\s*desc\\s*)", message = "{sort.type}") String sortType
    ) {
        if (pageSize == null)
            pageSize = defaultPageSize;
        return taskService.search(
                projectId,
                name,
                author,
                executor,
                type,
                priority,
                status,
                createdDate,
                pageNumber,
                pageSize,
                sortByParam,
                sortType
        );
    }

    @DeleteMapping("{id}")
    public HttpStatus deleteTask(
            @PathVariable @Min(value = 1L, message = "{path.variable.id}") Long projectId,
            @PathVariable @Min(value = 1L, message = "{path.variable.id}") Long id
    ) {
        return taskService.deleteTask(projectId, id);
    }
}