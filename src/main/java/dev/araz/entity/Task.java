package dev.araz.entity;

import java.sql.Date;
import java.sql.Timestamp;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.Lob;

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

    @Column(name = "description")
    @Lob
    private String description;
}