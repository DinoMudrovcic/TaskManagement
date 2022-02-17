package com.dinomudrovcic.taskmanagement.repository;

import com.dinomudrovcic.taskmanagement.domain.task.TaskTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TaskTimeRepository extends JpaRepository<TaskTime, Long> {
}
