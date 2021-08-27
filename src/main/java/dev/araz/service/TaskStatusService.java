package dev.araz.service;

import dev.araz.entity.TaskStatus;
import dev.araz.exception.NotTaskStatusException;
import dev.araz.repository.TaskStatusRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskStatusService {

    private final TaskStatusRepository statusRepository;

    public TaskStatus getStatusByName(String name) {
        Optional<TaskStatus> taskStatus = statusRepository.findTaskStatusByStatusName(name);
        if (taskStatus.isPresent())
            return taskStatus.get();
        throw new NotTaskStatusException("Task status " + name + " not exists!");
    }
}