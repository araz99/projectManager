package dev.araz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListProjectsRespDTO {
    private Long id;
    private String projectName;
    private String key;
    private String projectType;
    private String lead;
    private int quantityTasks;
    private int quantityEmployees;
}