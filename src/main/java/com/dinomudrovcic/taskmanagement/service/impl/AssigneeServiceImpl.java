package com.dinomudrovcic.taskmanagement.service.impl;

import com.dinomudrovcic.taskmanagement.domain.assignee.Assignee;
import com.dinomudrovcic.taskmanagement.model.request.AssigneeRequest;
import com.dinomudrovcic.taskmanagement.model.response.AssigneeResponse;
import com.dinomudrovcic.taskmanagement.repository.AssigneeRepository;
import com.dinomudrovcic.taskmanagement.service.AssigneeService;
import com.dinomudrovcic.taskmanagement.util.RepositoryUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssigneeServiceImpl implements AssigneeService {

    private final AssigneeRepository assigneeRepository;

    @Override
    public List<AssigneeResponse> getAllAssigneesResponse() {
        return assigneeRepository.findAll().stream()
                .map(assignee -> AssigneeResponse.builder()
                        .id(assignee.getId())
                        .assigneeName(assignee.getAssigneeName())
                        .assigneeSurname(assignee.getAssigneeSurname())
                        .build())
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public AssigneeResponse getAssigneeResponse(final AssigneeRequest request) {
        Assignee retrievedAssignee = assigneeRepository.getById(request.getAssignee_id());
        return AssigneeResponse.builder()
                .id(retrievedAssignee.getId())
                .assigneeName(retrievedAssignee.getAssigneeName())
                .assigneeSurname(retrievedAssignee.getAssigneeSurname())
                .build();
    }

    @Override
    public AssigneeResponse getAssigneeResponse(final Long id) {
        Assignee retrievedAssignee = assigneeRepository.getById(id);
        return AssigneeResponse.builder()
                .id(retrievedAssignee.getId())
                .assigneeName(retrievedAssignee.getAssigneeName())
                .assigneeSurname(retrievedAssignee.getAssigneeSurname())
                .build();
    }

    @Override
    @Transactional
    public AssigneeResponse saveAssigneeResponse(final AssigneeRequest request) {
        if (!RepositoryUtils.checkIfEntityExists(assigneeRepository, request.getAssignee_id())) {
            return AssigneeResponse.builder().build();
        }

        Assignee newAssignee = Assignee.builder()
                .assigneeName(request.getAssignee_name())
                .assigneeSurname(request.getAssignee_surname())
                .build();

        Assignee savedAssignee = assigneeRepository.save(newAssignee);
        return AssigneeResponse.builder()
                .id(savedAssignee.getId())
                .assigneeName(savedAssignee.getAssigneeName())
                .assigneeSurname(savedAssignee.getAssigneeSurname())
                .build();
    }

    @Override
    @Transactional
    public AssigneeResponse updateAssigneeResponse(final AssigneeRequest request) {
        if (!RepositoryUtils.checkIfEntityExists(assigneeRepository, request.getAssignee_id())) {
            return AssigneeResponse.builder().build();
        }

        Assignee updateAssignee = assigneeRepository.getById(request.getAssignee_id());
        updateAssignee.setAssigneeName(request.getAssignee_name());
        updateAssignee.setAssigneeSurname(request.getAssignee_surname());

        Assignee updatedAssignee = assigneeRepository.saveAndFlush(updateAssignee);
        return AssigneeResponse.builder()
                .id(updatedAssignee.getId())
                .assigneeName(updatedAssignee.getAssigneeName())
                .assigneeSurname(updatedAssignee.getAssigneeSurname())
                .build();
    }

    @Override
    @Transactional
    public boolean deleteAssignee(final Long assigneeId) {
        if (!RepositoryUtils.checkIfEntityExists(assigneeRepository, assigneeId)) {
            return false;
        }

        assigneeRepository.deleteById(assigneeId);

        return true;
    }

    @Override
    @Transactional
    public boolean deleteAssignee(final AssigneeRequest request) {
        if (!RepositoryUtils.checkIfEntityExists(assigneeRepository, request.getAssignee_id())) {
            return false;
        }

        assigneeRepository.deleteById(request.getAssignee_id());

        return true;
    }

    @Override
    public boolean assigneeExists(final Long assigneeId) {
        return RepositoryUtils.checkIfEntityExists(assigneeRepository, assigneeId);
    }

    @Override
    public Assignee getAssignee(final Long assigneeId) {
        return assigneeRepository.getById(assigneeId);
    }
}
