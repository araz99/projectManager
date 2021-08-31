package dev.araz.mapper;

import dev.araz.dto.TaskReqDTO;
import dev.araz.entity.Task;
import dev.araz.entity.TaskStatus;
import dev.araz.service.IssueTypeService;
import dev.araz.service.TaskPriorityService;
import dev.araz.service.TaskStatusService;
import dev.araz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class TaskReqMapper implements MapperToEntity<TaskReqDTO, Task> {

    private final UserService userService;
    private final IssueTypeService typeService;
    private final TaskPriorityService priorityService;
    private final TaskStatusService statusService;


    @Override
    public Task toEntity(TaskReqDTO dto) {
        return new Task(
                dto.getTaskName().trim(),
                userService.getAuthenticationUser(),
                typeService.getIssueTypeByName(dto.getIssueType().trim()),
                priorityService.getPriorityByName(dto.getPriority().trim()),
                statusService.getStatusByName("to do"),
                new Date(System.currentTimeMillis()),
                new Timestamp(System.currentTimeMillis()),
                dto.getDescription().trim()
                );
    }
}