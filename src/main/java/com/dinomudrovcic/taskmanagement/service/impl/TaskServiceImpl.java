package com.dinomudrovcic.taskmanagement.service.impl;

import com.dinomudrovcic.taskmanagement.domain.task.Task;
import com.dinomudrovcic.taskmanagement.domain.task.TaskStatus;
import com.dinomudrovcic.taskmanagement.model.request.TaskRequest;
import com.dinomudrovcic.taskmanagement.model.response.TaskResponse;
import com.dinomudrovcic.taskmanagement.repository.AssigneeRepository;
import com.dinomudrovcic.taskmanagement.repository.SubTaskRepository;
import com.dinomudrovcic.taskmanagement.repository.TaskGroupRepository;
import com.dinomudrovcic.taskmanagement.repository.TaskRepository;
import com.dinomudrovcic.taskmanagement.service.TaskService;
import com.dinomudrovcic.taskmanagement.util.RepositoryUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;

    private final AssigneeRepository assigneeRepository;

    private final TaskGroupRepository taskGroupRepository;

    private final SubTaskRepository subTaskRepository;

    @Override
    public List<TaskResponse> getAllTasks() {
        return taskRepository.findAll().stream()
                .map(task -> new TaskResponse(task))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public TaskResponse getTask(final TaskRequest request) {
        return new TaskResponse(taskRepository.getById(request.getTask_id()));
    }

    @Override
    public TaskResponse getTaskById(final Long id) {
        return new TaskResponse(taskRepository.getById(id));
    }

    @Override
    @Transactional
    public TaskResponse saveTask(final TaskRequest request) {
        if (RepositoryUtils.checkIfEntityExists(taskRepository, request.getTask_id())) {
            return TaskResponse.builder().build();
        }

        Task newTask = new Task();
        newTask.setName(request.getTask_name());
        newTask.setDescription(request.getTask_description());
        newTask.setTaskGroup(taskGroupRepository.getById(request.getTask_group_id()));
        newTask.setAssignee(assigneeRepository.getById(request.getTask_assignee_id()));
        newTask.setTaskStatus(TaskStatus.CREATED);
        newTask.setTotalDuration(0L);

        return new TaskResponse(taskRepository.saveAndFlush(newTask));

    }

    @Override
    @Transactional
    public TaskResponse updateTask(final TaskRequest request) {
        if (RepositoryUtils.checkIfEntityExists(taskRepository, request.getTask_id())) {
            return TaskResponse.builder().build();
        }

        Task updateTask = taskRepository.getById(request.getTask_id());
        updateTask.setName(request.getTask_name());
        updateTask.setDescription(request.getTask_description());
        updateTask.setTaskGroup(taskGroupRepository.getById(request.getTask_group_id()));
        updateTask.setAssignee(assigneeRepository.getById(request.getTask_assignee_id()));
        updateTask.setTaskStatus(TaskStatus.forValue(request.getTask_status_id()));

        return new TaskResponse(taskRepository.saveAndFlush(updateTask));
    }

    @Override
    public boolean deleteTask(final TaskRequest request) {
        if (!RepositoryUtils.checkIfEntityExists(taskRepository, request.getTask_id())) {
            return false;
        }

        subTaskRepository.deleteAllByTaskId(request.getTask_id());
        taskRepository.deleteById(request.getTask_id());

        return true;
    }

    @Override
    public boolean deleteTaskById(final Long id) {
        if (!RepositoryUtils.checkIfEntityExists(taskRepository, id)) {
            return false;
        }

        subTaskRepository.deleteAllByTaskId(id);
        taskRepository.deleteById(id);

        return true;
    }

    @Override
    @Transactional
    public TaskResponse taskAssign(final Long assigneeId, final Long taskId) {
        if (RepositoryUtils.checkIfEntityExists(taskRepository, taskId)) {
            return getTaskById(taskId);
        }

        Task taskToAssign = taskRepository.getById(taskId);
        taskToAssign.setAssignee(assigneeRepository.getById(assigneeId));
        taskRepository.saveAndFlush(taskToAssign);

        return new TaskResponse(taskToAssign);
    }

}
