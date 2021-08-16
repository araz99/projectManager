package dev.araz.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.Column;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.GenerationType;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "pm_user")
@Data
@NoArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username", nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @ManyToMany
    private Set<Role> roles;

    @Column(name = "registration_date", nullable = false)
    private Date registrationDate;

    @ManyToMany
    private Set<Project> projects;

    @OneToMany
    private Set<Task> tasks;
}