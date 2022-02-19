package com.dinomudrovcic.taskmanagement.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskGroupResponse {

    private Long task_group_id;

    private String task_group_name;

}
