package com.dinomudrovcic.taskmanagement.service;

import com.dinomudrovcic.taskmanagement.domain.task.TaskGroup;
import com.dinomudrovcic.taskmanagement.model.request.TaskGroupRequest;
import com.dinomudrovcic.taskmanagement.model.response.TaskGroupResponse;

import java.util.List;

public interface TaskGroupService {

    List<TaskGroupResponse> getAllTaskGroups();

    TaskGroupResponse getTaskGroup(TaskGroupRequest request);

    TaskGroupResponse getTaskGroup(Long id);

    TaskGroupResponse saveTaskGroup(TaskGroupRequest request);

    TaskGroupResponse updateTaskGroup(TaskGroupRequest request);

    boolean deleteTaskGroup(TaskGroupRequest request);

    boolean deleteTaskGroup(Long id);

    TaskGroup getGroupById(Long id);

}
