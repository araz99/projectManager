package dev.araz.controller;

import dev.araz.dto.ListTaskRespDTO;
import dev.araz.dto.TaskReqDTO;
import dev.araz.dto.TaskRespDTO;
import dev.araz.entity.Task;
import dev.araz.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Max;
import java.util.List;

@RestController
@RequestMapping("/projects/{projectId}/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @Value("${forPagination.pageSize}")
    private Integer defaultPageSize;

    @GetMapping
    public List<ListTaskRespDTO> allTasks(
            @PathVariable Long projectId,
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false) @Max(value = 15) Integer pageSize,
            @RequestParam(required = false, defaultValue = "id") String sortByParam,
            @RequestParam(required = false, defaultValue = "asc") String sortType) {
        if (pageSize == null)
            pageSize = defaultPageSize;
        return taskService.getTasks(projectId, pageNumber, pageSize, sortByParam, sortType);
    }

    @GetMapping("{id}")
    public ResponseEntity<TaskRespDTO> getProject(
            @PathVariable Long projectId,
            @PathVariable Long id) {
        return taskService.getTask(projectId, id);
    }

    @PostMapping
    public ResponseEntity<TaskRespDTO> addTask(
            @PathVariable Long projectId,
            @RequestBody TaskReqDTO dto
            ) {
        return taskService.addTask(projectId, dto);
    }

    @PutMapping
    public Task changeTask() {
        return null;
    }

    @GetMapping("/search")
    public List<Task> filter() {
        return null;
    }

    //DELETE task
}