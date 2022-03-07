package com.dinomudrovcic.taskmanagement.model.assignee.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssigneeRequest {

    private Long assigneeId;

    private String assigneeName;

    private String assigneeSurname;


}
