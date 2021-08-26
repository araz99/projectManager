package dev.araz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRespDTO {
    private Long id;
    private String projectName;
    private String key;
    private String projectType;
    private String lead;
    private int quantityTasks;
    private int quantityEmployees;
    private Date createdDate;
    private Timestamp lastModified;
    private String description;
}