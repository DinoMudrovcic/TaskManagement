package com.dinomudrovcic.taskmanagement.domain.task;

import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Entity
@Table(name="task", schema = "public")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = { "version" })
@ToString(exclude = { "version" })
@SequenceGenerator(name = "abstract_task_gen", sequenceName = "task_seq", allocationSize = 1)
@AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "task_name")),
        @AttributeOverride(name = "description", column = @Column(name = "task_description")),
        @AttributeOverride(name = "duration", column = @Column(name = "task_duration"))
})
public class Task extends AbstractTask {

    @ManyToOne(optional = false, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "task_group_id", foreignKey = @ForeignKey(name = "task_taskgroup_fk"), nullable = false)
    private TaskGroup taskGroup;

    /*@OneToMany(targetEntity = TaskTime.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<TaskTime> taskTime;

    @OneToMany(targetEntity = SubTask.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<SubTask> subTasks;*/

    @Column(name = "total_duration", nullable = false)
    @ColumnDefault("0")
    private Long totalDuration;

}