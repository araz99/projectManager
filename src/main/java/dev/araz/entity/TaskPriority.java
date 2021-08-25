package dev.araz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "task_priority")
@NoArgsConstructor
@Getter
@Setter
public class TaskPriority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "priority_name", nullable = false, unique = true)
    private String priorityName;

    @JsonIgnore
    @OneToMany(mappedBy = "priority")
    private Collection<Task> tasks;
}