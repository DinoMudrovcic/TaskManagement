package com.dinomudrovcic.taskmanagement.domain.task;

import com.dinomudrovcic.taskmanagement.domain.assignee.Assignee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name="task_time")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TaskTime {

    @Id
    @SequenceGenerator(name = "task_time_seq", sequenceName = "task_time_seq", allocationSize = 1)
    @GeneratedValue(generator = "task_time_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id")
    private Long id;

    @ManyToOne(optional = false, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "task_id", foreignKey = @ForeignKey(name = "tasktime_task_fk"))
    private Task task;

    @ManyToOne(optional = false, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "sub_task_id", foreignKey = @ForeignKey(name = "tasktime_subtask_fk"))
    private Subtask subTask;

    @ManyToOne(optional = false, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "assignee_id", foreignKey = @ForeignKey(name = "tasktime_assignee_fk"))
    private Assignee assignee;

    @Column(name = "start_time", nullable = false, columnDefinition = "DATE")
    private Date startTime;

    @Column(name = "end_time", columnDefinition = "DATE")
    private Date endTime;

}
