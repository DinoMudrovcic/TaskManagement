package com.dinomudrovcic.taskmanagement.model.request;

import lombok.Data;

@Data
public class TaskRequest {

    private Long task_id;

    private String task_name;

    private String task_description;

    private Long task_assignee_id;

    private Long task_group_id;

    private Long task_status_id;

    //TODO: check if builder handles this on Request
    public TaskRequest(final Long taskId){
        this.task_id = taskId;
    }

}
