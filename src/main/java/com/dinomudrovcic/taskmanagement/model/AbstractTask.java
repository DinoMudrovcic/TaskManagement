package com.dinomudrovcic.taskmanagement.model;

import com.dinomudrovcic.taskmanagement.domain.task.TaskStatus;
import com.dinomudrovcic.taskmanagement.domain.task.TaskTime;
import lombok.Builder;
import lombok.Data;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Collection;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@Data
public abstract class AbstractTask {

    @Id
    @GeneratedValue(generator = "abstract_task_seq", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Version
    @Column(name = "version")
    @ColumnDefault("1")
    private Integer version;

    @NotBlank
    @NotNull
    @Size(max = 50)
    private String name;

    @NotBlank
    @NotNull
    @Size(max = 255)
    private String description;

    @Column(name = "task_status", nullable = false, columnDefinition = "VARCHAR(50)")
    @Type(type = "com.dinomudrovcic.taskmanagement.domain.task.TaskStatus")
    private TaskStatus taskStatus;

    @Column(name = "duration")
    private Long duration;

}
