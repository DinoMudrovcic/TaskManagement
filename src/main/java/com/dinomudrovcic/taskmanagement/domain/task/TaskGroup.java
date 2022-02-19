package com.dinomudrovcic.taskmanagement.domain.task;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Table(name="task_group", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = { "version", "tasks" })
@ToString(exclude = { "version", "tasks" })
public class TaskGroup {

    @Id
    @SequenceGenerator(name = "task_group_seq", sequenceName = "task_group_seq", allocationSize = 1)
    @GeneratedValue(generator = "task_group_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @Version
    @Column(name = "version")
    @ColumnDefault("1")
    private Integer version;

    @NotBlank
    @NotNull
    @Size(max = 50)
    @Column(name = "task_group_name")
    private String taskGroupName;

}
