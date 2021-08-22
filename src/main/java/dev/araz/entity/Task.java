package dev.araz.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "task")
@Data
@NoArgsConstructor
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_name", nullable = false)
    private String taskName;

    @ManyToOne
    private User author;

    @ManyToOne
    private User executor;

    @ManyToOne
    private IssueType issueType;

    @ManyToOne
    private Project project;

    @ManyToOne
    private TaskPriority priority;

    @ManyToOne
    private TaskStatus status;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "last_modified", nullable = false)
    private Timestamp lastModified;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}