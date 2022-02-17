package com.dinomudrovcic.taskmanagement.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.boot.jackson.JsonComponent;

@Data
@Builder
public class AssigneeResponse {

    private Long id;

    private String assigneeName;

    private String assigneeSurname;

}
