package com.dinomudrovcic.taskmanagement.model.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AssigneeResponse {

    private Long id;

    private String assigneeName;

    private String assigneeSurname;

}
