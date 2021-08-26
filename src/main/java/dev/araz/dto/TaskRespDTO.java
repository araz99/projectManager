package dev.araz.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.sql.Timestamp;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TaskRespDTO {
    private Long id;
    private String taskName;
    private String author;
    private String executor;
    private String issueType;
    private String priority;
    private String status;
    private Date createdDate;
    private Timestamp lastModified;
    private String description;
}
