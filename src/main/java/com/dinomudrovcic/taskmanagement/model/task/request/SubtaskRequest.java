package com.dinomudrovcic.taskmanagement.model.task.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubtaskRequest {

    private Long subTaskId;

    private String subTaskName;

    private String subTaskDescription;

    private Long subTaskStatus;

    private Long taskId;

}
