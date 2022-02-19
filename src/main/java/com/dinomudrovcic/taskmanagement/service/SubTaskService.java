package com.dinomudrovcic.taskmanagement.service;

import com.dinomudrovcic.taskmanagement.domain.task.SubTask;
import com.dinomudrovcic.taskmanagement.domain.task.TaskStatus;
import com.dinomudrovcic.taskmanagement.model.request.SubTaskRequest;
import com.dinomudrovcic.taskmanagement.model.response.SubTaskResponse;

import java.util.List;

public interface SubTaskService {

    List<SubTaskResponse> getAllSubTasksResponse();

    SubTaskResponse getSubTaskResponse(SubTaskRequest request);

    SubTaskResponse getSubTaskResponse(Long subTaskId);

    SubTaskResponse saveSubTaskResponse(SubTaskRequest request);

    SubTaskResponse updateSubTaskResponse(SubTaskRequest request);

    boolean deleteSubTask(SubTaskRequest request);

    boolean deleteSubTask(Long subTaskId);

    boolean subTaskExists(Long subTaskId);

    SubTask getSubTask(Long subTaskId);

    void setStatus(Long subTaskId, TaskStatus status);

    void calculateDuration(Long subTaskId, Long duration);

}
