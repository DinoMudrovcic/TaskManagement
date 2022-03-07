package com.dinomudrovcic.taskmanagement.service.task;

public interface TaskTimeService {

    boolean endTaskTime(Long taskId);

    boolean endSubTaskTime(Long subTaskId);

    boolean taskAssign(Long assigneeId, Long taskId);

    boolean taskUnassign(Long taskId);

    boolean subTaskAssign(Long assigneeId, Long subTaskId);

    boolean subTaskUnassign(Long subTaskId);

}
