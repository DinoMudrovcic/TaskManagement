package com.dinomudrovcic.taskmanagement.service.impl;

import com.dinomudrovcic.taskmanagement.domain.task.TaskGroup;
import com.dinomudrovcic.taskmanagement.model.request.TaskGroupRequest;
import com.dinomudrovcic.taskmanagement.model.response.TaskGroupResponse;
import com.dinomudrovcic.taskmanagement.repository.TaskGroupRepository;
import com.dinomudrovcic.taskmanagement.repository.TaskRepository;
import com.dinomudrovcic.taskmanagement.service.TaskGroupService;
import com.dinomudrovcic.taskmanagement.util.RepositoryUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskGroupServiceImpl implements TaskGroupService {

    private final TaskGroupRepository taskGroupRepository;

    private final TaskRepository taskRepository;

    @Override
    public List<TaskGroupResponse> getAllTaskGroups() {
        return taskGroupRepository.findAll().stream()
                .map(taskGroup -> TaskGroupResponse.builder()
                        .taskGroupId(taskGroup.getId())
                        .taskGroupName(taskGroup.getTaskGroupName())
                        .build()
                )
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public TaskGroupResponse getTaskGroup(final TaskGroupRequest request) {
        TaskGroup retrievedTaskGroup = taskGroupRepository.getById(request.getTaskGroupId());
        return TaskGroupResponse.builder()
                .taskGroupId(retrievedTaskGroup.getId())
                .taskGroupName(retrievedTaskGroup.getTaskGroupName())
                .build();
    }

    @Override
    public TaskGroupResponse getTaskGroup(final Long id) {
        TaskGroup retrievedTaskGroup = taskGroupRepository.getById(id);
        return TaskGroupResponse.builder()
                .taskGroupId(retrievedTaskGroup.getId())
                .taskGroupName(retrievedTaskGroup.getTaskGroupName())
                .build();
    }

    @Override
    public TaskGroupResponse saveTaskGroup(final TaskGroupRequest request) {
        if (!RepositoryUtils.checkIfEntityExists(taskGroupRepository, request.getTaskGroupId())) {
            return TaskGroupResponse.builder().build();
        }

        TaskGroup newTaskGroup = new TaskGroup();
        newTaskGroup.setTaskGroupName(request.getTaskGroupName());

        TaskGroup savedTaskGroup = taskGroupRepository.saveAndFlush(newTaskGroup);

        return TaskGroupResponse.builder()
                .taskGroupId(savedTaskGroup.getId())
                .taskGroupName(savedTaskGroup.getTaskGroupName())
                .build();
    }

    @Override
    public TaskGroupResponse updateTaskGroup(final TaskGroupRequest request) {
        if (!RepositoryUtils.checkIfEntityExists(taskGroupRepository, request.getTaskGroupId())) {
            return TaskGroupResponse.builder().build();
        }

        TaskGroup updateTaskGroup = new TaskGroup();
        updateTaskGroup.setId(request.getTaskGroupId());
        updateTaskGroup.setTaskGroupName(request.getTaskGroupName());

        TaskGroup savedTaskGroup = taskGroupRepository.saveAndFlush(updateTaskGroup);

        return TaskGroupResponse.builder()
                .taskGroupId(savedTaskGroup.getId())
                .taskGroupName(savedTaskGroup.getTaskGroupName())
                .build();
    }

    @Override
    public boolean deleteTaskGroup(final TaskGroupRequest request) {
        if (!RepositoryUtils.checkIfEntityExists(taskGroupRepository, request.getTaskGroupId())
            && taskRepository.findAllByTaskGroupId(request.getTaskGroupId()).isEmpty()) {
            return false;
        }

        taskGroupRepository.deleteById(request.getTaskGroupId());

        return true;
    }

    @Override
    public boolean deleteTaskGroup(final Long id) {

        if (!RepositoryUtils.checkIfEntityExists(taskGroupRepository, id)
                && taskRepository.findAllByTaskGroupId(id).isEmpty()) {
            return false;
        }

        taskGroupRepository.deleteById(id);

        return true;
    }

    @Override
    public TaskGroup getGroupById(final Long id) {
        return taskGroupRepository.getById(id);
    }
}
