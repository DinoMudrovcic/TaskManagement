package com.dinomudrovcic.taskmanagement.service.impl;

import com.dinomudrovcic.taskmanagement.model.AssigneeRequest;
import com.dinomudrovcic.taskmanagement.model.AssigneeResponse;
import com.dinomudrovcic.taskmanagement.repository.AssigneeRepository;
import com.dinomudrovcic.taskmanagement.service.AssigneeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssigneeServiceImpl implements AssigneeService {

    private final AssigneeRepository assigneeRepository;

    @Override
    public List<AssigneeResponse> getAllAssignees() {
        return Collections.emptyList();
    }

    @Override
    public AssigneeResponse getAssignee(AssigneeRequest request) {
        return AssigneeResponse.builder().build();
    }

    @Override
    public AssigneeResponse getAssigneeById(Long id) {
        return AssigneeResponse.builder().build();
    }

    @Override
    public AssigneeResponse getAssigneeByName(String name) {
        return AssigneeResponse.builder().build();
    }

    @Override
    public AssigneeResponse saveAssignee(AssigneeRequest request) {
        return AssigneeResponse.builder().build();
    }

    @Override
    public AssigneeResponse updateAssignee(AssigneeRequest request) {
        return AssigneeResponse.builder().build();
    }

    @Override
    public AssigneeResponse deleteAssignee(Long assigneeId) {
        return AssigneeResponse.builder().build();
    }

    @Override
    public AssigneeResponse deleteAssignee(AssigneeRequest request) {
        return AssigneeResponse.builder().build();
    }
}
