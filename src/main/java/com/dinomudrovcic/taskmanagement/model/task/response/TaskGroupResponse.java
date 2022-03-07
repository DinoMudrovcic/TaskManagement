package com.dinomudrovcic.taskmanagement.model.task.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskGroupResponse {

    private Long taskGroupId;

    private String taskGroupName;

}
