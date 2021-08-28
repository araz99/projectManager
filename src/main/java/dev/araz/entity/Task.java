package dev.araz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;

@Entity
@Table(name = "task")
@NoArgsConstructor
@Getter
@Setter
@NamedNativeQuery(name = "Task.findAll",
        query = "select * from task t where t.project_id = ?1", resultClass = Task.class)
@NamedNativeQuery(name = "Task.findById",
        query = "select * from task t where t.project_id = ?1 and t.id = ?2", resultClass = Task.class)
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "task_name", nullable = false, unique = true)
    private String taskName;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "executor_id")
    private User executor;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "issue_type_id")
    private IssueType issueType;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "priority_id")
    private TaskPriority priority;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "status_id")
    private TaskStatus status;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "last_modified", nullable = false)
    private Timestamp lastModified;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    public Task(String taskName, User author, IssueType issueType, TaskPriority priority, TaskStatus status, Date createdDate, Timestamp lastModified, String description) {
        this.taskName = taskName;
        this.author = author;
        this.issueType = issueType;
        this.priority = priority;
        this.status = status;
        this.createdDate = createdDate;
        this.lastModified = lastModified;
        this.description = description;
    }

    public Task(String taskName, User executor, IssueType issueType, TaskPriority priority, TaskStatus status, Timestamp lastModified, String description) {
        this.taskName = taskName;
        this.executor = executor;
        this.issueType = issueType;
        this.priority = priority;
        this.status = status;
        this.lastModified = lastModified;
        this.description = description;
    }
}