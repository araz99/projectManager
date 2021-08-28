package dev.araz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TaskReqDTOForUpdate {
    private String taskName;
    private String executor;
    private String issueType;
    private String priority;
    private String status;
    private String description;
}