package com.dinomudrovcic.taskmanagement.repository;

import com.dinomudrovcic.taskmanagement.domain.task.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAllByTaskGroupId(final Long groupId);

    Optional<Task> findAllByNameContainsIgnoreCase(String name);

    Optional<Task> findAllByDescriptionContainsIgnoreCase(String description);

    Optional<Task> findAllByTaskStatus(String taskStatus);

    Optional<Task> findAllByDurationGreaterThanEqual(Long totalDuration);

    Optional<Task> findAllByDurationLessThanEqual(Long totalDuration);

    Optional<Task> findAllByTotalDurationGreaterThanEqual(Long totalDuration);

    Optional<Task> findAllByTotalDurationLessThanEqual(Long totalDuration);


}
