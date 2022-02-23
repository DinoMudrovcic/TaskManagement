package com.dinomudrovcic.taskmanagement.service.impl;

import com.dinomudrovcic.taskmanagement.domain.task.Task;
import com.dinomudrovcic.taskmanagement.model.response.TaskResponse;
import com.dinomudrovcic.taskmanagement.repository.TaskRepository;
import com.dinomudrovcic.taskmanagement.service.TaskFilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskFilterServiceImpl implements TaskFilterService {

    private final TaskRepository taskRepository;

    @Override
    public List<TaskResponse> getAllByNameLike(final String name) {
        return createTaskResponse(taskRepository.findAllByNameContainsIgnoreCase(name));
    }

    @Override
    public List<TaskResponse> getAllByDescriptionLike(final String description) {
        return createTaskResponse(taskRepository.findAllByDescriptionContainsIgnoreCase(description));
    }

    @Override
    public List<TaskResponse> getAllByTaskStatus(final String taskStatus) {
        return createTaskResponse(taskRepository.findAllByTaskStatus(taskStatus));
    }

    @Override
    public List<TaskResponse> getAllByDurationGreaterThanEqual(final Long duration) {
        return createTaskResponse(taskRepository.findAllByDurationGreaterThanEqual(duration));
    }

    @Override
    public List<TaskResponse> getAllByDurationLessThanEqual(final Long duration) {
        return createTaskResponse(taskRepository.findAllByDurationLessThanEqual(duration));
    }

    @Override
    public List<TaskResponse> getAllByTotalDurationGreaterThanEqual(final Long totalDuration) {
        return createTaskResponse(taskRepository.findAllByTotalDurationGreaterThanEqual(totalDuration));
    }

    @Override
    public List<TaskResponse> getAllByTotalDurationLessThanEqual(final Long totalDuration) {
        return createTaskResponse(taskRepository.findAllByTotalDurationLessThanEqual(totalDuration));
    }

    private List<TaskResponse> createTaskResponse(final Optional<Task> tasks) {
        if (tasks.isEmpty()) {
            return Collections.emptyList();
        }

        return tasks.stream()
                .map(task -> TaskResponse.builder()
                        .id(task.getId())
                        .taskName(task.getName())
                        .taskDescription(task.getDescription())
                        .taskGroup(task.getTaskGroup().getTaskGroupName())
                        .taskStatus(task.getTaskStatus().getCode())
                        .duration(task.getDuration())
                        .totalDuration(task.getTotalDuration())
                        .build()
                )
                .collect(Collectors.toUnmodifiableList());
    }
}
