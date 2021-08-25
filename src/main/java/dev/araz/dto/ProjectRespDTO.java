package dev.araz.dto;

import dev.araz.entity.Task;
import dev.araz.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProjectRespDTO {
    private Long id;
    private String projectName;
    private String key;
    private String projectType;
    private String lead;
    private Set<ListTaskRespDTO> tasks;
    private Set<UserProjectRespDTO> employees;
    private Date createdDate;
    private Timestamp lastModified;
    private String description;
}