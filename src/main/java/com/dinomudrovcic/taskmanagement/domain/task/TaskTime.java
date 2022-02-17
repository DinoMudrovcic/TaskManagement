package com.dinomudrovcic.taskmanagement.domain.task;

import com.dinomudrovcic.taskmanagement.domain.assignee.Assignee;
import com.dinomudrovcic.taskmanagement.model.AbstractTask;
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
    private AbstractTask task;

    @ManyToOne(optional = false, cascade = { CascadeType.MERGE, CascadeType.PERSIST })
    @JoinColumn(name = "assingee_id", foreignKey = @ForeignKey(name = "tasktime_assignee_fk"))
    private Assignee assignee;

    @Column(name = "start_time", nullable = false, columnDefinition = "DATE")
    private Date startTime;

    @Column(name = "end_time", columnDefinition = "DATE")
    private Date endTime;

    @Column(name = "duration", nullable = false)
    private Long duration;

}
