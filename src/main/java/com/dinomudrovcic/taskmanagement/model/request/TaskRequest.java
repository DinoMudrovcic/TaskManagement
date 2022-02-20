package com.dinomudrovcic.taskmanagement.model.request;

import lombok.Data;

import java.io.Serializable;

@Data
public class TaskRequest implements Serializable {

    private Long task_id;

    private String task_name;

    private String task_description;

    private Long task_assignee_id;

    private Long task_group_id;

}
