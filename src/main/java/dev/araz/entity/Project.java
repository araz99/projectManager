package dev.araz.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "project")
@Data
@NoArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(name = "key", nullable = false, length = 12)
    private String key;

    @ManyToOne
    private ProjectType projectType;

    @ManyToOne
    private User lead;

    @OneToMany(mappedBy = "project")
    private List<Task> tasks;

    @ManyToMany(mappedBy = "projects")
    private Set<User> users;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "last_modified", nullable = false)
    private Timestamp lastModified;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}