package com.dinomudrovcic.taskmanagement.service.impl;

import com.dinomudrovcic.taskmanagement.domain.task.TaskStatus;
import com.dinomudrovcic.taskmanagement.domain.task.TaskTime;
import com.dinomudrovcic.taskmanagement.repository.TaskTimeRepository;
import com.dinomudrovcic.taskmanagement.service.AssigneeService;
import com.dinomudrovcic.taskmanagement.service.SubTaskService;
import com.dinomudrovcic.taskmanagement.service.TaskService;
import com.dinomudrovcic.taskmanagement.service.TaskTimeService;
import com.dinomudrovcic.taskmanagement.util.CalculationUtils;
import com.dinomudrovcic.taskmanagement.util.DateUtils;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.jni.Local;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
public class TaskTimeServiceImpl implements TaskTimeService {

    private final TaskTimeRepository taskTimeRepository;

    private final TaskService taskService;

    private final AssigneeService assigneeService;

    private final SubTaskService subTaskService;

    @Override
    public boolean endTaskTime(final Long taskId) {
        TaskTime taskTime = taskTimeRepository.getTaskTimeByTaskIdAndEndTimeIsNull(taskId).stream().findFirst().orElse(null);

        if (!nonNull(taskTime)) return false;

        taskTime.setEndTime(DateUtils.getDate());
        taskService.calculateDuration(taskId, CalculationUtils.calculateDuration(taskTime.getStartTime(), taskTime.getEndTime()));

        taskTimeRepository.saveAndFlush(taskTime);

        taskService.setStatus(taskId, TaskStatus.DONE);

        return true;
    }

    @Override
    public boolean endSubTaskTime(final Long subTaskId) {
        TaskTime taskTime = taskTimeRepository.getTaskTimeBySubTaskIdAndEndTimeIsNull(subTaskId).stream().findFirst().orElse(null);

        if (!nonNull(taskTime)) return false;

        taskTime.setEndTime(DateUtils.getDate());
        subTaskService.calculateDuration(subTaskId, CalculationUtils.calculateDuration(taskTime.getStartTime(), taskTime.getEndTime()));

        taskTimeRepository.saveAndFlush(taskTime);

        subTaskService.setStatus(subTaskId, TaskStatus.DONE);

        return true;
    }

    @Override
    @Transactional
    public boolean taskAssign(final Long assigneeId, final Long taskId) {
        if (!(assigneeService.assigneeExists(assigneeId) && taskService.taskExists(taskId))) {
            return false;
        }

        TaskTime newTaskTime = new TaskTime();
        newTaskTime.setAssignee(assigneeService.getAssignee(assigneeId));
        newTaskTime.setTask(taskService.getTask(taskId));
        newTaskTime.setStartTime(DateUtils.getDate());

        taskService.setStatus(taskId, TaskStatus.ASSIGNED);

        taskTimeRepository.saveAndFlush(newTaskTime);

        return true;
    }

    @Override
    public boolean taskUnassign(final Long taskId) {
        if (!taskService.taskExists(taskId)) {
            return false;
        }

        TaskTime retrievedTaskTime = taskTimeRepository.getTaskTimeByTaskIdAndEndTimeIsNull(taskId).stream().findFirst().orElse(null);

        if (!nonNull(retrievedTaskTime)) return false;

        retrievedTaskTime.setEndTime(DateUtils.getDate());
        taskService.calculateDuration(taskId, CalculationUtils.calculateDuration(retrievedTaskTime.getStartTime(), retrievedTaskTime.getEndTime()));
        taskTimeRepository.saveAndFlush(retrievedTaskTime);

        taskService.setStatus(taskId, TaskStatus.UNASSIGNED);

        return true;
    }

    @Override
    public boolean subTaskAssign(final Long assigneeId, final Long subTaskId) {
        if (!(assigneeService.assigneeExists(assigneeId) && subTaskService.subTaskExists(subTaskId))) {
            return false;
        }

        TaskTime newTaskTime = new TaskTime();
        newTaskTime.setAssignee(assigneeService.getAssignee(assigneeId));
        newTaskTime.setSubTask(subTaskService.getSubTask(subTaskId));
        newTaskTime.setStartTime(DateUtils.getDate());

        subTaskService.setStatus(subTaskId, TaskStatus.ASSIGNED);

        taskTimeRepository.saveAndFlush(newTaskTime);

        return true;
    }

    @Override
    public boolean subTaskUnassign(final Long subTaskId) {
        if (!subTaskService.subTaskExists(subTaskId)) {
            return false;
        }

        TaskTime retrievedTaskTime = taskTimeRepository.getTaskTimeBySubTaskIdAndEndTimeIsNull(subTaskId).stream().findFirst().orElse(null);

        if (!nonNull(retrievedTaskTime)) return false;

        retrievedTaskTime.setEndTime(DateUtils.getDate());
        subTaskService.calculateDuration(subTaskId, CalculationUtils.calculateDuration(retrievedTaskTime.getStartTime(), retrievedTaskTime.getEndTime()));
        taskTimeRepository.saveAndFlush(retrievedTaskTime);

        subTaskService.setStatus(subTaskId, TaskStatus.UNASSIGNED);

        return true;
    }

}
