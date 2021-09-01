package dev.araz.service;

import dev.araz.dto.ListTaskRespDTO;
import dev.araz.dto.TaskReqDTO;
import dev.araz.dto.TaskReqDTOForUpdate;
import dev.araz.dto.TaskRespDTO;
import dev.araz.entity.*;
import dev.araz.mapper.MapperToDTO;
import dev.araz.mapper.MapperToEntity;
import dev.araz.repository.TaskRepository;
import dev.araz.specification.Specifications;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.sql.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectService projectService;
    private final UserService userService;
    private final IssueTypeService typeService;
    private final TaskPriorityService priorityService;
    private final TaskStatusService statusService;
    private final MapperToEntity<TaskReqDTO, Task> taskReqMapper;
    private final MapperToEntity<TaskReqDTOForUpdate, Task> updateTaskMapper;
    private final MapperToDTO<ListTaskRespDTO, Task> taskListMapper;
    private final MapperToDTO<TaskRespDTO, Task> taskRespMapper;

    // Task field names for Spring Specification
    private static final String Task_name = "taskName";
    private static final String Task_author = "author";
    private static final String Task_executor = "executor";
    private static final String Task_type = "issueType";
    private static final String Task_project = "project";
    private static final String Task_priority = "priority";
    private static final String Task_status = "status";
    private static final String Task_date = "createdDate";
    private static final String Task_last = "lastModified";
    private static final String Task_description = "description";


    @Override
    public List<ListTaskRespDTO> getTasks(Long projectId, Integer page, Integer size, String sortByParam, String type) {
        PageRequest pageRequest = getPageRequest(page, size, sortByParam.trim().toLowerCase(), type.trim().toLowerCase());
        return taskRepository.findAll(projectId, pageRequest)
                .stream().map(taskListMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<TaskRespDTO> getTask(Long projectId, Long id) {
        Optional<TaskRespDTO> taskRespDTO = taskRepository.findById(projectId, id)
                .map(taskRespMapper::toDTO);
        if (taskRespDTO.isPresent())
            return new ResponseEntity<>(taskRespDTO.get(), HttpStatus.OK);
         throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task id = " + id + "  is not exists!");
    }

    @Override
    public ResponseEntity<TaskRespDTO> addTask(Long projectId, TaskReqDTO dto) {
        Task saveTask = taskReqMapper.toEntity(dto);
        saveTask.setProject(projectService.getProjectById(projectId));
        Task savedTask = taskRepository.save(saveTask);
        return new ResponseEntity<>(taskRespMapper.toDTO(savedTask), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity updateTask(Long projectId, Long id, TaskReqDTOForUpdate dto) {
        Optional<Task> optionalTask = taskRepository.findById(projectId, id);
        if (optionalTask.isEmpty())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task id = " + id + " not exists!");
        Task task = updateTaskMapper.toEntity(dto);
        taskRepository.update(
                task.getTaskName(),
                task.getExecutor().getId(),
                task.getIssueType().getId(),
                task.getPriority().getId(),
                task.getStatus().getId(),
                task.getDescription(),
                projectId,
                id);
        return new ResponseEntity(HttpStatus.OK);
    }

    @Override
    public List<TaskRespDTO> search(Long projectId, String name, String author, String executor, String type, String priority, String status, Date createdDate, Integer pageNumber, Integer pageSize, String sortByParam, String sortType) {
        Project project = projectService.getProjectById(projectId);
        User taskAuthor = author == null ? null : userService.getUserByName(author.trim());
        User taskExecutor = executor == null ? null : userService.getUserByName(executor.trim());
        IssueType taskType = type == null ? null : typeService.getIssueTypeByName(type.trim());
        TaskPriority taskPriority = priority == null ? null : priorityService.getPriorityByName(priority.trim());
        TaskStatus taskStatus = status == null ? null : statusService.getStatusByName(status.trim());
        return taskRepository.findAll(
                Specifications.<Task, Project>has(Task_project, project)
                        .and(Specifications.has(Task_name, name))
                        .and(Specifications.has(Task_author, taskAuthor))
                        .and(Specifications.has(Task_executor, taskExecutor))
                        .and(Specifications.has(Task_type, taskType))
                        .and(Specifications.has(Task_priority, taskPriority))
                        .and(Specifications.has(Task_status, taskStatus))
                        .and(Specifications.has(Task_date, createdDate)),
                getPageRequest(pageNumber, pageSize, sortByParam.trim().toLowerCase(), sortType.trim().toLowerCase())
        )
                .stream().map(taskRespMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public HttpStatus deleteTask(Long projectId, Long id) {
        Optional<Task> task = taskRepository.findById(projectId, id);
        if (task.isEmpty())
            return HttpStatus.BAD_REQUEST;
        taskRepository.deleteById(id);
        return HttpStatus.OK;
    }

    private PageRequest getPageRequest(Integer page, Integer size, String sortByParam, String type) {
        if (type.equals("desc"))
            return PageRequest.of(page, size, Sort.by(sortByParam).descending());
        else
            return PageRequest.of(page, size, Sort.by(sortByParam).ascending());
    }
}