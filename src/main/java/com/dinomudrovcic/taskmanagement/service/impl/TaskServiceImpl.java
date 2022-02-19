package com.dinomudrovcic.taskmanagement.service.impl;

import com.dinomudrovcic.taskmanagement.domain.task.Task;
import com.dinomudrovcic.taskmanagement.domain.task.TaskStatus;
import com.dinomudrovcic.taskmanagement.model.request.TaskRequest;
import com.dinomudrovcic.taskmanagement.model.response.TaskResponse;
import com.dinomudrovcic.taskmanagement.repository.SubTaskRepository;
import com.dinomudrovcic.taskmanagement.repository.TaskGroupRepository;
import com.dinomudrovcic.taskmanagement.repository.TaskRepository;
import com.dinomudrovcic.taskmanagement.service.TaskGroupService;
import com.dinomudrovcic.taskmanagement.service.TaskService;
import com.dinomudrovcic.taskmanagement.util.RepositoryUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final SubTaskRepository subTaskRepository;

    private final TaskGroupService taskGroupService;

    @Override
    public List<TaskResponse> getAllTasksResponse() {
        return taskRepository.findAll().stream()
                .map(task -> TaskResponse.builder()
                        .id(task.getId())
                        .taskName(task.getName())
                        .taskDescription(task.getDescription())
                        .taskGroup(task.getTaskGroup().getTaskGroupName())
                        .taskStatus(task.getTaskStatus().getCode())
                        .duration(task.getDuration())
                        .totalDuration(task.getTotalDuration())
                        .build()
                )
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public TaskResponse getTaskResponse(final TaskRequest request) {
        Task retrievedTask = taskRepository.getById(request.getTask_id());
        return TaskResponse.builder()
                .id(retrievedTask.getId())
                .taskName(retrievedTask.getName())
                .taskDescription(retrievedTask.getDescription())
                .taskGroup(retrievedTask.getTaskGroup().getTaskGroupName())
                .taskStatus(retrievedTask.getTaskStatus().getCode())
                .duration(retrievedTask.getDuration())
                .totalDuration(retrievedTask.getTotalDuration())
                .build();
    }

    @Override
    public TaskResponse getTaskResponse(final Long id) {
        Task retrievedTask = taskRepository.getById(id);
        return TaskResponse.builder()
                .id(retrievedTask.getId())
                .taskName(retrievedTask.getName())
                .taskDescription(retrievedTask.getDescription())
                .taskGroup(retrievedTask.getTaskGroup().getTaskGroupName())
                .taskStatus(retrievedTask.getTaskStatus().getCode())
                .duration(retrievedTask.getDuration())
                .totalDuration(retrievedTask.getTotalDuration())
                .build();
    }

    @Override
    @Transactional
    public TaskResponse saveTaskResponse(final TaskRequest request) {
        if (!RepositoryUtils.checkIfEntityExists(taskRepository, request.getTask_id())) {
            return TaskResponse.builder().build();
        }

        Task newTask = new Task();
        newTask.setName(request.getTask_name());
        newTask.setDescription(request.getTask_description());
        newTask.setTaskGroup(taskGroupService.getGroupById(request.getTask_group_id()));
        newTask.setTaskStatus(TaskStatus.CREATED);

        Task savedTask = taskRepository.save(newTask);

        return TaskResponse.builder()
                .id(savedTask.getId())
                .taskName(savedTask.getName())
                .taskDescription(savedTask.getDescription())
                .taskGroup(savedTask.getTaskGroup().getTaskGroupName())
                .taskStatus(savedTask.getTaskStatus().getCode())
                .duration(savedTask.getDuration())
                .totalDuration(savedTask.getTotalDuration())
                .build();
    }

    @Override
    @Transactional
    public TaskResponse updateTaskResponse(final TaskRequest request) {
        if (!RepositoryUtils.checkIfEntityExists(taskRepository, request.getTask_id())) {
            return TaskResponse.builder().build();
        }

        Task updateTask = taskRepository.getById(request.getTask_id());
        updateTask.setName(request.getTask_name());
        updateTask.setDescription(request.getTask_description());
        updateTask.setTaskGroup(taskGroupService.getGroupById(request.getTask_group_id()));

        Task updatedTask = taskRepository.save(updateTask);

        return TaskResponse.builder()
                .id(updatedTask.getId())
                .taskName(updatedTask.getName())
                .taskDescription(updatedTask.getDescription())
                .taskGroup(updatedTask.getTaskGroup().getTaskGroupName())
                .taskStatus(updatedTask.getTaskStatus().getCode())
                .duration(updatedTask.getDuration())
                .totalDuration(updatedTask.getTotalDuration())
                .build();
    }

    @Override
    public boolean deleteTask(final TaskRequest request) {
        if (!RepositoryUtils.checkIfEntityExists(taskRepository, request.getTask_id())
            && subTaskRepository.findAllByTaskId(request.getTask_id()).isEmpty()) {
            return false;
        }

        taskRepository.deleteById(request.getTask_id());

        return true;
    }

    @Override
    public boolean deleteTask(final Long id) {
        if (!RepositoryUtils.checkIfEntityExists(taskRepository, id)
                && subTaskRepository.findAllByTaskId(id).isEmpty()) {
            return false;
        }

        taskRepository.deleteById(id);

        return true;
    }

    @Override
    @Transactional
    public void calculateDuration(final Long taskId, final Long duration) {
        Task updateTask = taskRepository.getById(taskId);
        updateTask.setDuration(updateTask.getDuration() + duration);
        updateTask.setTotalDuration(updateTask.getTotalDuration() + duration);
        taskRepository.saveAndFlush(updateTask);
    }

    @Override
    public boolean taskExists(final Long taskId) {
        return RepositoryUtils.checkIfEntityExists(taskRepository, taskId);
    }

    @Override
    public Task getTask(final Long taskId) {
        return taskRepository.getById(taskId);
    }

    @Override
    @Transactional
    public void setStatus(final Long taskId, final TaskStatus status) {
        Task retrievedTask = taskRepository.getById(taskId);
        retrievedTask.setTaskStatus(status);
        taskRepository.saveAndFlush(retrievedTask);
    }

    @Override
    public void calculateTotalDuration(final Long taskId, final Long duration) {
        Task updateTask = taskRepository.getById(taskId);
        updateTask.setTotalDuration(updateTask.getTotalDuration() + duration);
        taskRepository.saveAndFlush(updateTask);
    }

    @Override
    public List<Task> getTasksByGroup(Long groupId) {
        return taskRepository.findAllByTaskGroupId(groupId);
    }

}
