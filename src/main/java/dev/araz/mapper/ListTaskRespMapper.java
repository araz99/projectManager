package dev.araz.mapper;

import dev.araz.dto.ListTaskRespDTO;
import dev.araz.entity.Task;
import org.springframework.stereotype.Component;

@Component
public class ListTaskRespMapper implements MapperToDTO<ListTaskRespDTO, Task> {
    @Override
    public ListTaskRespDTO toDTO(Task task) {
        return new ListTaskRespDTO(
                task.getId(),
                task.getTaskName(),
                task.getAuthor().getUsername(),
                task.getExecutor() == null ? "" : task.getExecutor().getUsername(),
                task.getIssueType().getIssueTypeName(),
                task.getPriority() == null ? "" : task.getPriority().getPriorityName(),
                task.getStatus().getStatusName()
        );
    }
}