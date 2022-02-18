package com.dinomudrovcic.taskmanagement.model.request;

import lombok.Data;

@Data
public class AssigneeRequest {

    private Long assignee_id;

    private String assignee_name;

    private String assignee_surname;

    //TODO: check if builder handles this on Request
    public AssigneeRequest(final Long assigneeId){
        this.assignee_id = assigneeId;
    }

}
