package com.dinomudrovcic.taskmanagement.domain.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;

public enum TaskStatus implements Serializable {

    CREATED("CREATED", 0L),
    ASSIGNED("ASSIGNED", 1L),
    WORKING("WORKING",2L),
    TESTING("TESTING",3L),
    REVISION("REVISION",4L),
    DONE("DONE", 5L),
    ARCHIVED("ARCHIVED", 6L);

    private final String code;

    private final Long value;

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

}
