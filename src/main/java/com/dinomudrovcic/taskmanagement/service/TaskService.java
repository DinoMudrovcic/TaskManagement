package com.dinomudrovcic.taskmanagement.service;

import com.dinomudrovcic.taskmanagement.model.TaskRequest;
import com.dinomudrovcic.taskmanagement.model.TaskResponse;

import java.util.List;

public interface TaskService {

    List<TaskResponse> getAllTasks();

    TaskResponse getTask(TaskRequest request);

    TaskResponse getTaskById(Long id);

    TaskResponse getTaskByName(String name);

    TaskResponse saveTask(TaskRequest request);

    TaskResponse updateTask(TaskRequest request);

    TaskResponse deleteTask(TaskRequest request);

    TaskResponse deleteTaskById(Long id);

}
