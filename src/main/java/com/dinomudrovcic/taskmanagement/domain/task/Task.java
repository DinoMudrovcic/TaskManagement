package com.dinomudrovcic.taskmanagement.domain.task;

import com.dinomudrovcic.taskmanagement.model.AbstractTask;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name="task")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = { "version", "taskTime", "subTasks" })
@ToString(exclude = { "version", "taskTime", "subTasks" })
@SequenceGenerator(name = "abstract_task_seq", sequenceName = "task_seq", allocationSize = 1)
@AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "task_name")),
        @AttributeOverride(name = "description", column = @Column(name = "task_description")),
        @AttributeOverride(name = "duration", column = @Column(name = "task_duration"))
})
public class Task extends AbstractTask {

    @ManyToOne(optional = false, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "task_group_id", foreignKey = @ForeignKey(name = "task_taskgroup_fk"), nullable = false)
    private TaskGroup taskGroup;

    @OneToMany(targetEntity = TaskTime.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<TaskTime> taskTime;

    @OneToMany(targetEntity = SubTask.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<SubTask> subTasks;

    @Column(name = "total_duration")
    private Long totalDuration;

}