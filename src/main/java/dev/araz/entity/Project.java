package dev.araz.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.Set;

@Entity
@Table(name = "project")
@NoArgsConstructor
@Getter
@Setter
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_name", nullable = false)
    private String projectName;

    @Column(name = "key", nullable = false, length = 12)
    private String key;

    @ManyToOne(optional = false, cascade = CascadeType.ALL)
    @JoinColumn(name = "project_type_id")
    private ProjectType projectType;

    @ManyToOne(optional = false, cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "lead_id")
    private User lead;

    @OneToMany(mappedBy = "project")
    private Set<Task> tasks;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable(name = "pm_user_projects",
                joinColumns = @JoinColumn(name = "projects_id"),
                inverseJoinColumns = @JoinColumn(name = "users_id"))
    private Set<User> employees;

    @Column(name = "created_date", nullable = false)
    private Date createdDate;

    @Column(name = "last_modified", nullable = false)
    private Timestamp lastModified;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;
}