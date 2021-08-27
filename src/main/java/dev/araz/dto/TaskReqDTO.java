package dev.araz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskReqDTO {
    private String taskName;
    private String author;
    private String issueType;
    private String priority;
    private String description;
}