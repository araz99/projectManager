package dev.araz.service;

import dev.araz.dto.ListTaskRespDTO;
import dev.araz.dto.TaskReqDTO;
import dev.araz.dto.TaskRespDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface TaskService {
    List<ListTaskRespDTO> getTasks(Long projectId, Integer page, Integer size, String sortByParam, String type);

    ResponseEntity<TaskRespDTO> getTask(Long projectId, Long id);

    ResponseEntity<TaskRespDTO> addTask(Long projectId, TaskReqDTO dto);
}