package com.dinomudrovcic.taskmanagement.model.assignee.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssigneeResponse {

    private Long assigneeId;

    private String assigneeName;

    private String assigneeSurname;

}
