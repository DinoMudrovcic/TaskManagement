package com.dinomudrovcic.taskmanagement.domain.assignee;

import com.dinomudrovcic.taskmanagement.domain.task.Task;
import com.dinomudrovcic.taskmanagement.domain.task.TaskTime;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name="assignee")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = { "taskTime" })
@ToString(exclude = { "taskTime" })
public class Assignee {

    @Id
    @SequenceGenerator(name = "assignee_seq", sequenceName = "assignee_seq", allocationSize = 1)
    @GeneratedValue(generator = "assignee_seq", strategy = GenerationType.SEQUENCE)
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

    @OneToMany(mappedBy = "assignee", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<TaskTime> taskTimeCollection;

    @OneToMany(mappedBy = "assignee", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<Task> tasks;

    public String getAssigneeFullName() {
        return String.format("{} {}", assigneeName, assigneeSurname);
    }

}
