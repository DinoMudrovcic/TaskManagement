package com.dinomudrovcic.taskmanagement.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SubTaskResponse {

    private Long id;

    private String sub_task_name;

    private String sub_task_description;

    private String sub_task_status;

    private Long duration;

    private Long task_id;

}
