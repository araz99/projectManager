package dev.araz.service;

import dev.araz.entity.TaskPriority;
import dev.araz.exception.NotTaskPriorityException;
import dev.araz.repository.TaskPriorityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskPriorityService {

    private final TaskPriorityRepository priorityRepository;

    public TaskPriority getPriorityByName(String name) {
        Optional<TaskPriority> taskPriority = priorityRepository.findTaskPriorityByPriorityName(name);
        if (taskPriority.isPresent())
            return taskPriority.get();
        throw new NotTaskPriorityException("Task priority " + name + " not exists!");
    }
}