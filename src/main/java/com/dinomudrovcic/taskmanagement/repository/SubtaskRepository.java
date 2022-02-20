package com.dinomudrovcic.taskmanagement.repository;

import com.dinomudrovcic.taskmanagement.domain.task.Subtask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubtaskRepository extends JpaRepository<Subtask, Long> {

    List<Subtask> findAllByTaskId(long taskId);

}
