package com.dinomudrovcic.taskmanagement.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssigneeResponse {

    private Long id;

    private String assigneeName;

    private String assigneeSurname;

}
