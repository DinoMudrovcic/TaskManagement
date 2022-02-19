package com.dinomudrovcic.taskmanagement.repository;

import com.dinomudrovcic.taskmanagement.domain.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByTaskGroupId(final Long groupId);

}
