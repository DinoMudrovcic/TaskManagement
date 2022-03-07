package com.dinomudrovcic.taskmanagement.service.task;

import com.dinomudrovcic.taskmanagement.domain.task.Task;
import com.dinomudrovcic.taskmanagement.domain.task.TaskStatus;
import com.dinomudrovcic.taskmanagement.model.task.request.TaskRequest;
import com.dinomudrovcic.taskmanagement.model.task.response.TaskResponse;

import java.util.List;

public interface TaskService {

    List<TaskResponse> getAllTasksResponse();

    TaskResponse getTaskResponse(TaskRequest request);

    TaskResponse getTaskResponse(Long id);

    TaskResponse saveTaskResponse(TaskRequest request);

    TaskResponse updateTaskResponse(TaskRequest request);

    boolean deleteTask(TaskRequest request);

    boolean deleteTask(Long id);

    void calculateDuration(Long taskId, Long duration);

    boolean taskExists(Long taskId);

    Task getTask(Long taskId);

    void setStatus(Long taskId, TaskStatus status);

    void calculateTotalDuration(Long taskId, Long duration);

    List<Task> getTasksByGroup(Long groupId);

}
