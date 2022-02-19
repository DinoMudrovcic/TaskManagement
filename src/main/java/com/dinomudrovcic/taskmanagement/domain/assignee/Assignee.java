package com.dinomudrovcic.taskmanagement.domain.assignee;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="assignee")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = { "taskTime" })
@ToString(exclude = { "taskTime" })
@SequenceGenerator(name = "assignee_gen", sequenceName = "assignee_seq", allocationSize = 1)
public class Assignee {

    @Id
    @GeneratedValue(generator = "assignee_gen", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @NotBlank
    @NotNull
    @Size(max = 50)
    @Column(name = "assignee_name")
    private String assigneeName;

    @NotBlank
    @NotNull
    @Size(max = 100)
    @Column(name = "assignee_surname")
    private String assigneeSurname;

    /*@OneToMany(mappedBy = "assignee", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<TaskTime> taskTimeCollection;*/

    public String getAssigneeFullName() {
        return String.format("{} {}", assigneeName, assigneeSurname);
    }

}
