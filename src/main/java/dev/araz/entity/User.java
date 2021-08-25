package dev.araz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;
import java.util.Set;

@Entity
@Table(name = "pm_user")
@NoArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Role> roles;

    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;

    @JsonIgnore
    @OneToMany(mappedBy = "lead")
    private Collection<Project> ProjectsForLead;

    @JsonIgnore
    @ManyToMany(mappedBy = "users")
    private Set<Project> projects;

    @JsonIgnore
    @OneToMany(mappedBy = "executor")
    private Collection<Task> tasksForExecutor;

    @JsonIgnore
    @OneToMany(mappedBy = "author")
    private Collection<Task> tasksForAuthor;
}