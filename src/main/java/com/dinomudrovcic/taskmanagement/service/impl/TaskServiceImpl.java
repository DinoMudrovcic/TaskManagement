package com.dinomudrovcic.taskmanagement.service.impl;

import com.dinomudrovcic.taskmanagement.model.TaskRequest;
import com.dinomudrovcic.taskmanagement.model.TaskResponse;
import com.dinomudrovcic.taskmanagement.repository.TaskRepository;
import com.dinomudrovcic.taskmanagement.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    @Override
    public List<TaskResponse> getAllTasks() {
        return Collections.emptyList();
    }

    @Override
    public TaskResponse getTask(TaskRequest request) {
        return TaskResponse.builder().build();
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        return TaskResponse.builder().build();
    }

    @Override
    public TaskResponse getTaskByName(String name) {
        return TaskResponse.builder().build();
    }

    @Override
    public TaskResponse saveTask(TaskRequest request) {
        return TaskResponse.builder().build();
    }

    @Override
    public TaskResponse updateTask(TaskRequest request) {
        return TaskResponse.builder().build();
    }

    @Override
    public TaskResponse deleteTask(TaskRequest request) {
        return TaskResponse.builder().build();
    }

    @Override
    public TaskResponse deleteTaskById(Long id) {
        return TaskResponse.builder().build();
    }
}
