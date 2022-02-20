package com.dinomudrovcic.taskmanagement.service;

import com.dinomudrovcic.taskmanagement.model.response.TaskResponse;

import java.util.List;

public interface TaskFilterService {

    List<TaskResponse> getAllByNameLike(String name);

    List<TaskResponse> getAllByDescriptionLike(String description);

    List<TaskResponse> getAllByTaskStatus(String taskStatus);

    List<TaskResponse> getAllByDurationGreaterThanEqual(Long duration);

    List<TaskResponse> getAllByDurationLessThanEqual(Long duration);

    List<TaskResponse> getAllByTotalDurationGreaterThanEqual(Long totalDuration);

    List<TaskResponse> getAllByTotalDurationLessThanEqual(Long totalDuration);
    
}
