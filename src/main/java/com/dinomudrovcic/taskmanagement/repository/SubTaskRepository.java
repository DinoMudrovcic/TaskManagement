package com.dinomudrovcic.taskmanagement.repository;

import com.dinomudrovcic.taskmanagement.domain.task.SubTask;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubTaskRepository extends JpaRepository<SubTask, Long> {

    void deleteAllByTaskId(Long taskId);

}
