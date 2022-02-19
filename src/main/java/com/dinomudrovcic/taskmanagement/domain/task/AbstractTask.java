package com.dinomudrovcic.taskmanagement.domain.task;

import lombok.Data;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@MappedSuperclass
@Data
public abstract class AbstractTask {

    @Id
    @GeneratedValue(generator = "abstract_task_gen", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
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

    @Column(name = "task_status", nullable = false, columnDefinition = "VARCHAR(255)")
    @Enumerated(EnumType.STRING)
    private TaskStatus taskStatus;

    @Column(name = "duration", nullable = false)
    @ColumnDefault("0")
    private Long duration;

}