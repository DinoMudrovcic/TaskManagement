package com.dinomudrovcic.taskmanagement.service;

import com.dinomudrovcic.taskmanagement.model.request.AssigneeRequest;
import com.dinomudrovcic.taskmanagement.model.response.AssigneeResponse;

import java.util.List;

public interface AssigneeService {

    List<AssigneeResponse> getAllAssignees();

    AssigneeResponse getAssignee(AssigneeRequest request);

    AssigneeResponse getAssigneeById(Long id);

    AssigneeResponse saveAssignee(AssigneeRequest request);

    AssigneeResponse updateAssignee(AssigneeRequest request);

    boolean deleteAssignee(Long assigneeId);

    boolean deleteAssignee(AssigneeRequest request);

}
