package dev.araz.service;

import dev.araz.dto.ListTaskRespDTO;
import dev.araz.dto.TaskRespDTO;
import dev.araz.entity.Task;
import dev.araz.mapper.MapperToDTO;
import dev.araz.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final MapperToDTO<ListTaskRespDTO, Task> taskMapper;

    @Override
    public List<ListTaskRespDTO> getTasks(Long projectId, Integer page, Integer size, String sortByParam, String type) {
        PageRequest pageRequest = getPageRequest(page, size, sortByParam, type);
        return taskRepository.findAll(projectId, pageRequest)
                .stream().map(taskMapper::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseEntity<TaskRespDTO> getTask(Long projectId, Long id) {
        return null;
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