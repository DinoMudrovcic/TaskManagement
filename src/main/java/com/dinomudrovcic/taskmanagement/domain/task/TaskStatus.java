package com.dinomudrovcic.taskmanagement.domain.task;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Optional;

public enum TaskStatus implements Serializable {

    CREATED("CREATED"),
    ASSIGNED("ASSIGNED"),
    WORKING("WORKING"),
    TESTING("TESTING"),
    REVISION("REVISION"),
    DONE("DONE"),
    ARCHIVED("ARCHIVED");

    private final String code;

    TaskStatus(final String code) {
        this.code = code;
    }

    @JsonCreator
    public static TaskStatus forValue(final String value) {
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
