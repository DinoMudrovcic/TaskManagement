package com.dinomudrovcic.taskmanagement.model.request;

import lombok.Data;

@Data
public class SubTaskRequest {

    private Long sub_task_id;

    private String sub_task_name;

    private String sub_task_description;

    private Long sub_task_status;

    private Long task_id;

}
