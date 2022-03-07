package com.dinomudrovcic.taskmanagement.service.task.impl;

import com.dinomudrovcic.taskmanagement.domain.task.Subtask;
import com.dinomudrovcic.taskmanagement.domain.task.Task;
import com.dinomudrovcic.taskmanagement.domain.task.TaskStatus;
import com.dinomudrovcic.taskmanagement.model.task.request.SubtaskRequest;
import com.dinomudrovcic.taskmanagement.model.task.response.SubtaskResponse;
import com.dinomudrovcic.taskmanagement.repository.task.SubtaskRepository;
import com.dinomudrovcic.taskmanagement.service.task.SubtaskService;
import com.dinomudrovcic.taskmanagement.service.task.TaskService;
import com.dinomudrovcic.taskmanagement.util.RepositoryUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubtaskServiceImpl implements SubtaskService {

    private final SubtaskRepository subTaskRepository;

    private final TaskService taskService;

    @Override
    public List<SubtaskResponse> getAllSubTasksResponse() {
        return subTaskRepository.findAll().stream()
                .map(subtask -> SubtaskResponse.builder()
                        .subtaskId(subtask.getId())
                        .subtaskName(subtask.getName())
                        .subtaskDescription(subtask.getDescription())
                        .subtaskStatus(subtask.getTaskStatus().getCode())
                        .duration(subtask.getDuration())
                        .taskId(subtask.getTask().getId())
                        .build()
                )
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public SubtaskResponse getSubTaskResponse(final SubtaskRequest request) {
        Subtask retrievedSubtask = subTaskRepository.getById(request.getTaskId());
        return SubtaskResponse.builder()
                .subtaskId(retrievedSubtask.getId())
                .subtaskName(retrievedSubtask.getName())
                .subtaskDescription(retrievedSubtask.getDescription())
                .subtaskStatus(retrievedSubtask.getTaskStatus().getCode())
                .duration(retrievedSubtask.getDuration())
                .taskId(retrievedSubtask.getTask().getId())
                .build();
    }

    @Override
    public SubtaskResponse getSubTaskResponse(final Long subTaskId) {
        Subtask retrievedSubtask = subTaskRepository.getById(subTaskId);
        return SubtaskResponse.builder()
                .subtaskId(retrievedSubtask.getId())
                .subtaskName(retrievedSubtask.getName())
                .subtaskDescription(retrievedSubtask.getDescription())
                .subtaskStatus(retrievedSubtask.getTaskStatus().getCode())
                .duration(retrievedSubtask.getDuration())
                .taskId(retrievedSubtask.getTask().getId())
                .build();
    }

    @Override
    public SubtaskResponse saveSubTaskResponse(final SubtaskRequest request) {
        if (!(RepositoryUtils.checkIfEntityExists(subTaskRepository, request.getSubTaskId())
            && taskService.taskExists(request.getTaskId()))) {
            return SubtaskResponse.builder().build();
        }

        Task parentTask = taskService.getTask(request.getTaskId());

        Subtask newSubtask = new Subtask();
        newSubtask.setName(request.getSubTaskName());
        newSubtask.setDescription(request.getSubTaskDescription());
        newSubtask.setTaskStatus(TaskStatus.CREATED);
        newSubtask.setTask(parentTask);
        newSubtask.setDuration(0L);

        Subtask savedSubtask = subTaskRepository.save(newSubtask);

        return SubtaskResponse.builder()
                .subtaskId(savedSubtask.getId())
                .subtaskName(savedSubtask.getName())
                .subtaskDescription(savedSubtask.getDescription())
                .subtaskStatus(savedSubtask.getTaskStatus().getCode())
                .duration(savedSubtask.getDuration())
                .taskId(savedSubtask.getTask().getId())
                .build();
    }

    @Override
    public SubtaskResponse updateSubTaskResponse(final SubtaskRequest request) {
        if (!(RepositoryUtils.checkIfEntityExists(subTaskRepository, request.getSubTaskId())
                && taskService.taskExists(request.getTaskId()))) {
            return SubtaskResponse.builder().build();
        }

        Task parentTask = taskService.getTask(request.getTaskId());

        Subtask updateSubtask = subTaskRepository.getById(request.getSubTaskId());
        updateSubtask.setName(request.getSubTaskName());
        updateSubtask.setDescription(request.getSubTaskDescription());
        updateSubtask.setTask(parentTask);

        Subtask savedSubtask = subTaskRepository.saveAndFlush(updateSubtask);

        return SubtaskResponse.builder()
                .subtaskId(savedSubtask.getId())
                .subtaskName(savedSubtask.getName())
                .subtaskDescription(savedSubtask.getDescription())
                .subtaskStatus(savedSubtask.getTaskStatus().getCode())
                .duration(savedSubtask.getDuration())
                .taskId(savedSubtask.getTask().getId())
                .build();
    }

    @Override
    public boolean deleteSubTask(final SubtaskRequest request) {
        if (!RepositoryUtils.checkIfEntityExists(subTaskRepository, request.getSubTaskId())) {
            return false;
        }

        subTaskRepository.deleteById(request.getSubTaskId());

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
    public Subtask getSubTask(final Long subTaskId) {
        return subTaskRepository.getById(subTaskId);
    }

    @Override
    public void setStatus(final Long subTaskId, final TaskStatus status) {
        Subtask retrievedSubtask = subTaskRepository.getById(subTaskId);
        retrievedSubtask.setTaskStatus(status);
        subTaskRepository.saveAndFlush(retrievedSubtask);
    }

    @Override
    @Transactional
    public void calculateDuration(final Long subTaskId, final Long duration) {
        Subtask updateSubtask = subTaskRepository.getById(subTaskId);

        updateSubtask.setDuration(updateSubtask.getDuration() + duration);
        taskService.calculateTotalDuration(updateSubtask.getTask().getId(), duration);

        subTaskRepository.saveAndFlush(updateSubtask);
    }
}
