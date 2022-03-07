package com.dinomudrovcic.taskmanagement.repository.task;

import com.dinomudrovcic.taskmanagement.domain.task.TaskGroup;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskGroupRepository extends JpaRepository<TaskGroup, Long> {
}
