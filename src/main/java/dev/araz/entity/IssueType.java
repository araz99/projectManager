package dev.araz.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "issue_type")
@NoArgsConstructor
@Getter
@Setter
public class IssueType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "issue_type_name", nullable = false)
    private String issueTypeName;

    @JsonIgnore
    @OneToMany(mappedBy = "issueType")
    private Collection<Task> tasks;
}