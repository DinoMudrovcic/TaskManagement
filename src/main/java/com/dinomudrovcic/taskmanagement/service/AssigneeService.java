package com.dinomudrovcic.taskmanagement.service;

import com.dinomudrovcic.taskmanagement.domain.assignee.Assignee;
import com.dinomudrovcic.taskmanagement.model.request.AssigneeRequest;
import com.dinomudrovcic.taskmanagement.model.response.AssigneeResponse;

import java.util.List;

public interface AssigneeService {

    List<AssigneeResponse> getAllAssigneesResponse();

    AssigneeResponse getAssigneeResponse(AssigneeRequest request);

    AssigneeResponse getAssigneeResponse(Long id);

    AssigneeResponse saveAssigneeResponse(AssigneeRequest request);

    AssigneeResponse updateAssigneeResponse(AssigneeRequest request);

    boolean deleteAssignee(Long assigneeId);

    boolean deleteAssignee(AssigneeRequest request);

    boolean assigneeExists(Long assingeeId);

    Assignee getAssignee(Long assigneeId);

}
