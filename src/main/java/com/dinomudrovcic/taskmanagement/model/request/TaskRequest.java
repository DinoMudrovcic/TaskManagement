package com.dinomudrovcic.taskmanagement.model.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskRequest {

    private Long taskId;

    private String taskName;

    private String taskDescription;

    private Long taskAssigneeId;

    private Long taskGroupId;

}
