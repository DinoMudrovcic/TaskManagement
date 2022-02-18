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
    public List<AssigneeResponse> getAllAssignees() {
        return assigneeRepository.findAll().stream()
                .map(assignee -> new AssigneeResponse(assignee))
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public AssigneeResponse getAssignee(final AssigneeRequest request) {
        return new AssigneeResponse(assigneeRepository.getById(request.getAssignee_id()));
    }

    @Override
    public AssigneeResponse getAssigneeById(final Long id) {
        return new AssigneeResponse(assigneeRepository.getById(id));
    }

    @Override
    @Transactional
    public AssigneeResponse saveAssignee(final AssigneeRequest request) {
        if (RepositoryUtils.checkIfEntityExists(assigneeRepository, request.getAssignee_id())) {
            return AssigneeResponse.builder().build();
        }

        Assignee newAssignee = Assignee.builder()
                .assigneeName(request.getAssignee_name())
                .assigneeSurname(request.getAssignee_surname())
                .build();

        return new AssigneeResponse(assigneeRepository.saveAndFlush(newAssignee));
    }

    @Override
    @Transactional
    public AssigneeResponse updateAssignee(final AssigneeRequest request) {
        if (!RepositoryUtils.checkIfEntityExists(assigneeRepository, request.getAssignee_id())) {
            return AssigneeResponse.builder().build();
        }

        Assignee updateAssignee = assigneeRepository.getById(request.getAssignee_id());
        updateAssignee.setAssigneeName(request.getAssignee_name());
        updateAssignee.setAssigneeSurname(request.getAssignee_surname());

        return new AssigneeResponse(assigneeRepository.saveAndFlush(updateAssignee));
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
}
