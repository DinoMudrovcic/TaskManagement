package com.dinomudrovcic.taskmanagement.domain.task;

import com.dinomudrovcic.taskmanagement.model.AbstractTask;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name="sub_task")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode(exclude = { "version", "taskTime" })
@ToString(exclude = { "version", "taskTime" })
@SequenceGenerator(name = "abstract_task_seq", sequenceName = "sub_task_seq", allocationSize = 1)
@AttributeOverrides({
        @AttributeOverride(name = "name", column = @Column(name = "sub_task_name")),
        @AttributeOverride(name = "description", column = @Column(name = "sub_task_description")),
        @AttributeOverride(name = "duration", column = @Column(name = "sub_task_duration"))
})
public class SubTask extends AbstractTask implements Serializable {

    @OneToMany(targetEntity = TaskTime.class, cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Collection<TaskTime> taskTime;

    @ManyToOne(optional = false, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "task_id", foreignKey = @ForeignKey(name = "task_subtask_fk"), nullable = false)
    private Task task;
}
