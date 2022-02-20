package com.dinomudrovcic.taskmanagement.service.impl;

import com.dinomudrovcic.taskmanagement.domain.task.SubTask;
import com.dinomudrovcic.taskmanagement.domain.task.Task;
import com.dinomudrovcic.taskmanagement.domain.task.TaskStatus;
import com.dinomudrovcic.taskmanagement.model.request.SubTaskRequest;
import com.dinomudrovcic.taskmanagement.model.response.SubTaskResponse;
import com.dinomudrovcic.taskmanagement.repository.SubTaskRepository;
import com.dinomudrovcic.taskmanagement.service.SubTaskService;
import com.dinomudrovcic.taskmanagement.service.TaskService;
import com.dinomudrovcic.taskmanagement.util.RepositoryUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubTaskServiceImpl implements SubTaskService {

    private final SubTaskRepository subTaskRepository;

    private final TaskService taskService;

    @Override
    public List<SubTaskResponse> getAllSubTasksResponse() {
        return subTaskRepository.findAll().stream()
                .map(subTask -> SubTaskResponse.builder()
                        .id(subTask.getId())
                        .sub_task_name(subTask.getName())
                        .sub_task_description(subTask.getDescription())
                        .sub_task_status(subTask.getTaskStatus().getCode())
                        .duration(subTask.getDuration())
                        .task_id(subTask.getTask().getId())
                        .build()
                )
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public SubTaskResponse getSubTaskResponse(final SubTaskRequest request) {
        SubTask retrievedSubTask = subTaskRepository.getById(request.getTask_id());
        return SubTaskResponse.builder()
                .id(retrievedSubTask.getId())
                .sub_task_name(retrievedSubTask.getName())
                .sub_task_description(retrievedSubTask.getDescription())
                .sub_task_status(retrievedSubTask.getTaskStatus().getCode())
                .duration(retrievedSubTask.getDuration())
                .task_id(retrievedSubTask.getTask().getId())
                .build();
    }

    @Override
    public SubTaskResponse getSubTaskResponse(final Long subTaskId) {
        SubTask retrievedSubTask = subTaskRepository.getById(subTaskId);
        return SubTaskResponse.builder()
                .id(retrievedSubTask.getId())
                .sub_task_name(retrievedSubTask.getName())
                .sub_task_description(retrievedSubTask.getDescription())
                .sub_task_status(retrievedSubTask.getTaskStatus().getCode())
                .duration(retrievedSubTask.getDuration())
                .task_id(retrievedSubTask.getTask().getId())
                .build();
    }

    @Override
    public SubTaskResponse saveSubTaskResponse(final SubTaskRequest request) {
        if (!(RepositoryUtils.checkIfEntityExists(subTaskRepository, request.getSub_task_id())
            && taskService.taskExists(request.getTask_id()))) {
            return SubTaskResponse.builder().build();
        }

        Task parentTask = taskService.getTask(request.getTask_id());

        SubTask newSubTask = new SubTask();
        newSubTask.setName(request.getSub_task_name());
        newSubTask.setDescription(request.getSub_task_description());
        newSubTask.setTaskStatus(TaskStatus.CREATED);
        newSubTask.setTask(parentTask);
        newSubTask.setDuration(0L);

        SubTask savedSubTask = subTaskRepository.save(newSubTask);

        return SubTaskResponse.builder()
                .id(savedSubTask.getId())
                .sub_task_name(savedSubTask.getName())
                .sub_task_description(savedSubTask.getDescription())
                .sub_task_status(savedSubTask.getTaskStatus().getCode())
                .duration(savedSubTask.getDuration())
                .task_id(savedSubTask.getTask().getId())
                .build();
    }

    @Override
    public SubTaskResponse updateSubTaskResponse(final SubTaskRequest request) {
        if (!(RepositoryUtils.checkIfEntityExists(subTaskRepository, request.getSub_task_id())
                && taskService.taskExists(request.getTask_id()))) {
            return SubTaskResponse.builder().build();
        }

        Task parentTask = taskService.getTask(request.getTask_id());

        SubTask updateSubTask = subTaskRepository.getById(request.getSub_task_id());
        updateSubTask.setName(request.getSub_task_name());
        updateSubTask.setDescription(request.getSub_task_description());
        updateSubTask.setTask(parentTask);

        SubTask savedSubTask = subTaskRepository.saveAndFlush(updateSubTask);

        return SubTaskResponse.builder()
                .id(savedSubTask.getId())
                .sub_task_name(savedSubTask.getName())
                .sub_task_description(savedSubTask.getDescription())
                .sub_task_status(savedSubTask.getTaskStatus().getCode())
                .duration(savedSubTask.getDuration())
                .task_id(savedSubTask.getTask().getId())
                .build();
    }

    @Override
    public boolean deleteSubTask(final SubTaskRequest request) {
        if (!RepositoryUtils.checkIfEntityExists(subTaskRepository, request.getSub_task_id())) {
            return false;
        }

        subTaskRepository.deleteById(request.getSub_task_id());

        return true;
    }

    @Override
    public boolean deleteSubTask(final Long subTaskId) {
        if (!RepositoryUtils.checkIfEntityExists(subTaskRepository, subTaskId)) {
            return false;
        }

        subTaskRepository.deleteById(subTaskId);

        return true;
    }

    @Override
    public boolean subTaskExists(final Long subTaskId) {
        return RepositoryUtils.checkIfEntityExists(subTaskRepository, subTaskId);
    }

    @Override
    public SubTask getSubTask(final Long subTaskId) {
        return subTaskRepository.getById(subTaskId);
    }

    @Override
    public void setStatus(final Long subTaskId, final TaskStatus status) {
        SubTask retrievedSubTask = subTaskRepository.getById(subTaskId);
        retrievedSubTask.setTaskStatus(status);
        subTaskRepository.saveAndFlush(retrievedSubTask);
    }

    @Override
    @Transactional
    public void calculateDuration(final Long subTaskId, final Long duration) {
        SubTask updateSubTask = subTaskRepository.getById(subTaskId);

        updateSubTask.setDuration(updateSubTask.getDuration() + duration);
        taskService.calculateTotalDuration(updateSubTask.getTask().getId(), duration);

        subTaskRepository.saveAndFlush(updateSubTask);
    }
}
