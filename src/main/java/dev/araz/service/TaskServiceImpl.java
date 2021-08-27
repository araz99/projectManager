package dev.araz.service;

import dev.araz.dto.ListTaskRespDTO;
import dev.araz.dto.TaskReqDTO;
import dev.araz.dto.TaskRespDTO;
import dev.araz.entity.Task;
import dev.araz.mapper.MapperToDTO;
import dev.araz.mapper.MapperToEntity;
import dev.araz.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final ProjectService projectService;
    private final MapperToEntity<TaskReqDTO, Task> taskReqMapper;
    private final MapperToDTO<ListTaskRespDTO, Task> taskListMapper;
    private final MapperToDTO<TaskRespDTO, Task> taskRespMapper;

    @Override
    public List<ListTaskRespDTO> getTasks(Long projectId, Integer page, Integer size, String sortByParam, String type) {
        PageRequest pageRequest = getPageRequest(page, size, sortByParam, type);
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

    private PageRequest getPageRequest(Integer page, Integer size, String sortByParam, String type) {
        PageRequest pageRequest;
        if (type.equals("desc"))
            pageRequest = PageRequest.of(page, size, Sort.by(sortByParam).descending());
        else
            pageRequest = PageRequest.of(page, size, Sort.by(sortByParam).ascending());
        return pageRequest;
    }
}