package com.dinomudrovcic.taskmanagement.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TaskResponse {

    private Long id;

    private String taskName;

    private String taskDescription;

    private String taskGroup;

    private String taskStatus;

    private Long duration;

    private Long totalDuration;

}
