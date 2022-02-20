package com.dinomudrovcic.taskmanagement.model.request;

import lombok.Data;

@Data
public class TaskGroupRequest {

    private Long task_group_id;

    private String task_group_name;

}
