package com.dinomudrovcic.taskmanagement.domain.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;

public enum TaskStatus implements Serializable {

    CREATED("CREATED", 0L),
    ASSIGNED("ASSIGNED", 1L),
    UNASSIGNED("UNASSIGNED", 2L),
    DONE("DONE", 3L);

    private String code;

    private Long value;

    TaskStatus(final String code, final Long value) {
        this.code = code;
        this.value = value;
    }

    @JsonCreator
    public static TaskStatus forValue(final Long value) {
        return Optional.ofNullable(value)
            .map(it ->
                Arrays.stream(TaskStatus.values())
                    .filter(taskStatus ->
                        it.equals(taskStatus.code))
                    .findFirst()
                    .orElse(null))
            .orElse(null);
    }

    @JsonValue
    public String getCode() {
        return this.code;
    }

    @JsonValue
    public void setCode(String code) {
        this.code = code;
    }

}
