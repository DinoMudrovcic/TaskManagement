package com.dinomudrovcic.taskmanagement.repository;

import com.dinomudrovcic.taskmanagement.domain.task.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubTaskRepository extends JpaRepository<SubTask, Long> {

    List<SubTask> findAllByTaskId(long taskId);

}
