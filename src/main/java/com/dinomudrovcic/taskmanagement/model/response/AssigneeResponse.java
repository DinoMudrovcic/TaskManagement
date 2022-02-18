package com.dinomudrovcic.taskmanagement.model.response;

import com.dinomudrovcic.taskmanagement.domain.assignee.Assignee;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AssigneeResponse {

    private Long id;

    private String assigneeName;

    private String assigneeSurname;

    public AssigneeResponse(final Assignee assignee) {
        this.id = assignee.getId();
        this.assigneeName = assignee.getAssigneeName();
        this.assigneeSurname = assignee.getAssigneeSurname();
    }

}
