package com.dinomudrovcic.taskmanagement.service;

import com.dinomudrovcic.taskmanagement.model.AssigneeRequest;
import com.dinomudrovcic.taskmanagement.model.AssigneeResponse;

import java.util.List;

public interface AssigneeService {

    List<AssigneeResponse> getAllAssignees();

    AssigneeResponse getAssignee(AssigneeRequest request);

    AssigneeResponse getAssigneeById(Long id);

    AssigneeResponse getAssigneeByName(String name);

    AssigneeResponse saveAssignee(AssigneeRequest request);

    AssigneeResponse updateAssignee(AssigneeRequest request);

    AssigneeResponse deleteAssignee(Long assigneeId);

    AssigneeResponse deleteAssignee(AssigneeRequest request);

}
