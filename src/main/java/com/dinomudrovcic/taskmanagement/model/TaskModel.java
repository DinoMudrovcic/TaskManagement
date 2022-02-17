package com.dinomudrovcic.taskmanagement.model;

import lombok.Data;

@Data
public class TaskModel {

    private String taskName;

    private String taskDescription;

    private String taskGroup;

    private String taskStatus;

    private Long assigneId;

}
