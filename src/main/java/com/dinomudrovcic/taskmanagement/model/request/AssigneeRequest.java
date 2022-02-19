package com.dinomudrovcic.taskmanagement.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
public class AssigneeRequest {

    @JsonProperty
    private Long assignee_id;

    @JsonProperty
    private String assignee_name;

    @JsonProperty
    private String assignee_surname;


}
