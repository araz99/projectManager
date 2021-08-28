package dev.araz.mapper;

import dev.araz.dto.TaskReqDTOForUpdate;
import dev.araz.entity.Task;
import dev.araz.service.IssueTypeService;
import dev.araz.service.TaskPriorityService;
import dev.araz.service.TaskStatusService;
import dev.araz.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Component
@RequiredArgsConstructor
public class TaskReqMapperForUpdate implements MapperToEntity<TaskReqDTOForUpdate, Task> {

    private final UserService userService;
    private final IssueTypeService typeService;
    private final TaskPriorityService priorityService;
    private final TaskStatusService statusService;

    @Override
    public Task toEntity(TaskReqDTOForUpdate dto) {
        return new Task(
                dto.getTaskName(),
                userService.getUserByName(dto.getExecutor()),
                typeService.getIssueTypeByName(dto.getIssueType()),
                priorityService.getPriorityByName(dto.getPriority()),
                statusService.getStatusByName(dto.getStatus()),
                new Timestamp(System.currentTimeMillis()),
                dto.getDescription()
                );
    }
}