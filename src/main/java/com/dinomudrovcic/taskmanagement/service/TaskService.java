package com.dinomudrovcic.taskmanagement.service;

import com.dinomudrovcic.taskmanagement.model.request.TaskRequest;
import com.dinomudrovcic.taskmanagement.model.response.TaskResponse;

import java.util.List;

public interface TaskService {

    List<TaskResponse> getAllTasks();

    TaskResponse getTask(TaskRequest request);

    TaskResponse getTaskById(Long id);

    TaskResponse saveTask(TaskRequest request);

    TaskResponse updateTask(TaskRequest request);

    boolean deleteTask(TaskRequest request);

    boolean deleteTaskById(Long id);

    TaskResponse taskAssign(Long assigneeId, Long taskId);

}
