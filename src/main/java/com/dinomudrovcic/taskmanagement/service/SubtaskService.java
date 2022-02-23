package com.dinomudrovcic.taskmanagement.service;

import com.dinomudrovcic.taskmanagement.domain.task.Subtask;
import com.dinomudrovcic.taskmanagement.domain.task.TaskStatus;
import com.dinomudrovcic.taskmanagement.model.request.SubtaskRequest;
import com.dinomudrovcic.taskmanagement.model.response.SubtaskResponse;

import java.util.List;

public interface SubtaskService {

    List<SubtaskResponse> getAllSubTasksResponse();

    SubtaskResponse getSubTaskResponse(SubtaskRequest request);

    SubtaskResponse getSubTaskResponse(Long subTaskId);

    SubtaskResponse saveSubTaskResponse(SubtaskRequest request);

    SubtaskResponse updateSubTaskResponse(SubtaskRequest request);

    boolean deleteSubTask(SubtaskRequest request);

    boolean deleteSubTask(Long subTaskId);

    boolean subTaskExists(Long subTaskId);

    Subtask getSubTask(Long subTaskId);

    void setStatus(Long subTaskId, TaskStatus status);

    void calculateDuration(Long subTaskId, Long duration);

}
