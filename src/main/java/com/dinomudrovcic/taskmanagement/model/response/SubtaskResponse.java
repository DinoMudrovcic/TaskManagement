package com.dinomudrovcic.taskmanagement.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubtaskResponse {

    private Long subtaskId;

    private String subtaskName;

    private String subtaskDescription;

    private String subtaskStatus;

    private Long duration;

    private Long taskId;

}
