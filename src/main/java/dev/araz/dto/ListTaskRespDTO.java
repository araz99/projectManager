package dev.araz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListTaskRespDTO {
    private Long id;
    private String taskName;
    private String author;
    private String executor;
    private String issueType;
    private String priority;
    private String status;
}