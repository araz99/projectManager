package dev.araz.mapper;

import dev.araz.dto.TaskRespDTO;
import dev.araz.entity.Task;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaskRespMapper implements MapperToDTO<TaskRespDTO, Task> {
    @Override
    public TaskRespDTO toDTO(Task task) {
        return new TaskRespDTO(
                task.getId(),
                task.getTaskName(),
                task.getAuthor().getUsername(),
                task.getExecutor() == null ? "" : task.getExecutor().getUsername(),
                task.getIssueType().getIssueTypeName(),
                task.getPriority() == null ? "" : task.getPriority().getPriorityName(),
                task.getStatus().getStatusName(),
                task.getCreatedDate(),
                task.getLastModified(),
                task.getDescription()
        );
    }
}