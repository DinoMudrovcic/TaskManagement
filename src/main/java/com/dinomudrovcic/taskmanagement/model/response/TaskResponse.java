package com.dinomudrovcic.taskmanagement.model.response;

import com.dinomudrovcic.taskmanagement.domain.task.Task;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskResponse {

    private Long task_id;

    private String task_name;

    private String task_description;

    private String task_assignee;

    private String task_group;

    private String task_status;

    private Long total_duration;

    public TaskResponse(Task task) {
        this.task_id = task.getId();
        this.task_name = task.getName();
        this.task_description = task.getDescription();
        this.task_assignee = task.getAssignee().getAssigneeFullName();
        this.task_group = task.getTaskGroup().getTaskGroupName();
        this.task_status = task.getTaskStatus().getCode();
        this.total_duration = task.getTotalDuration();
    }

}
