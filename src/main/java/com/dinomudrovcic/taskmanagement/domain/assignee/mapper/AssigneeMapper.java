package com.dinomudrovcic.taskmanagement.domain.assignee.mapper;

import com.dinomudrovcic.taskmanagement.domain.assignee.entity.Assignee;
import com.dinomudrovcic.taskmanagement.model.assignee.request.AssigneeRequest;
import com.dinomudrovcic.taskmanagement.model.assignee.response.AssigneeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public abstract class AssigneeMapper {

    @Mapping(source = "id", target = "assigneeId")
    public abstract AssigneeResponse toDto(final Assignee assignee);

    @Mapping(source = "assigneeId", target = "id")
    public abstract Assignee fromDto(final AssigneeRequest assignee);

    public abstract void update(@MappingTarget Assignee assignee, final AssigneeRequest assigneeRequest);

}
