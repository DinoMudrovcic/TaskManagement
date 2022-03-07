package com.dinomudrovcic.taskmanagement.repository.task;

import com.dinomudrovcic.taskmanagement.domain.task.TaskTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskTimeRepository extends JpaRepository<TaskTime, Long> {

    Optional<TaskTime> getTaskTimeByTaskIdAndEndTimeIsNull(final Long taskId);

    Optional<TaskTime> getTaskTimeBySubTaskIdAndEndTimeIsNull(final Long subTaskId);

}
