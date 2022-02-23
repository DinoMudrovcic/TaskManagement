package com.dinomudrovcic.taskmanagement.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssigneeRequest {

    private Long assigneeId;

    private String assigneeName;

    private String assigneeSurname;


}
