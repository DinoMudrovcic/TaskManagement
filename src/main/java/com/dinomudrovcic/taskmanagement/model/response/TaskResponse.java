package com.dinomudrovcic.taskmanagement.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
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
