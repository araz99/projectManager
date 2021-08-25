package dev.araz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "project_type")
@NoArgsConstructor
@Getter
@Setter
public class ProjectType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "project_type_name", nullable = false, unique = true)
    private String projectTypeName;

    @JsonIgnore
    @OneToMany(mappedBy = "projectType")
    private Collection<Project> projects;
}