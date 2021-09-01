package dev.araz.service;

import dev.araz.dto.ListTaskRespDTO;
import dev.araz.dto.TaskReqDTO;
import dev.araz.dto.TaskReqDTOForUpdate;
import dev.araz.dto.TaskRespDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public interface TaskService {
    List<ListTaskRespDTO> getTasks(Long projectId, Integer page, Integer size, String sortByParam, String type);

    ResponseEntity<TaskRespDTO> getTask(Long projectId, Long id);

    ResponseEntity<TaskRespDTO> addTask(Long projectId, TaskReqDTO dto);

    ResponseEntity updateTask(Long projectId, Long id, TaskReqDTOForUpdate dto);

    List<TaskRespDTO> search(Long projectId, String name, String author, String executor, String type, String priority, String status, Date createdDate, Integer pageNumber, Integer pageSize, String sortByParam, String sortType);

    HttpStatus deleteTask(Long projectId, Long id);
}