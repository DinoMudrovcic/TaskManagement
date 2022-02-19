package com.dinomudrovcic.taskmanagement.sevice;

import com.dinomudrovcic.taskmanagement.domain.assignee.Assignee;
import com.dinomudrovcic.taskmanagement.model.request.AssigneeRequest;
import com.dinomudrovcic.taskmanagement.model.response.AssigneeResponse;
import com.dinomudrovcic.taskmanagement.repository.AssigneeRepository;
import com.dinomudrovcic.taskmanagement.service.impl.AssigneeServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AssigneeServiceUnitTests {

    @Mock
    private static AssigneeRepository assigneeRepository;

    @InjectMocks
    private AssigneeServiceImpl assigneeService;

    @BeforeEach
    public void init() {

        Assignee assignee = Assignee.builder().id(1L).assigneeName("test").assigneeSurname("test").build();

        when(assigneeRepository.findAll()).thenReturn(Collections.singletonList(assignee));
        when(assigneeRepository.getById(assignee.getId())).thenReturn(assignee);
        when(assigneeRepository.save(any(Assignee.class))).thenReturn(assignee);
    }

    @Test
    public void testGetAllAssigneesResponse() {
        List<AssigneeResponse> assigneeResponses = assigneeService.getAllAssigneesResponse();

        assertTrue(assigneeResponses.size() == 1);
    }

    @Test
    public void testGetAssigneeResponseByRequest() {
        AssigneeRequest assigneeRequest = AssigneeRequest.builder().assignee_id(1L).build();
        AssigneeResponse assigneeResponse = assigneeService.getAssigneeResponse(assigneeRequest.getAssignee_id());

        assertNotNull(assigneeResponse);
    }

    @Test
    public void testGetAssigneeResponseById() {
        AssigneeResponse assigneeResponse = assigneeService.getAssigneeResponse(1L);

        assertNotNull(assigneeResponse);
    }

    @Test
    public void testSaveAssigneeResponse() {
        AssigneeRequest assigneeRequest = AssigneeRequest.builder().assignee_id(1L).assignee_name("test").assignee_surname("test").build();
        AssigneeResponse expectedAssigneeResponse = AssigneeResponse.builder().id(1L).assigneeName("test").assigneeSurname("test").build();

        AssigneeResponse actualAssigneeResponse = assigneeService.saveAssigneeResponse(assigneeRequest);

        assertEquals(expectedAssigneeResponse, actualAssigneeResponse);
    }

    @Test
    public void testUpdateAssigneeResponse() {
        AssigneeRequest assigneeRequestSave = AssigneeRequest.builder().assignee_id(1L).assignee_name("test1").assignee_surname("test1").build();
        AssigneeRequest assigneeRequestUpdate = AssigneeRequest.builder().assignee_id(1L).assignee_name("test").assignee_surname("test").build();
        AssigneeResponse expectedAssigneeResponse = AssigneeResponse.builder().id(1L).assigneeName("test").assigneeSurname("test").build();

        assigneeService.saveAssigneeResponse(assigneeRequestSave);
        AssigneeResponse actualAssigneeResponse = assigneeService.saveAssigneeResponse(assigneeRequestUpdate);

        assertEquals(expectedAssigneeResponse, actualAssigneeResponse);
    }

    @Test
    public void testDeleteAssigneeById() {
        final boolean result = assigneeService.deleteAssignee(1L);
        Mockito.verify(assigneeRepository, Mockito.times(1)).deleteById(1L);
        assertTrue(result);
    }

    @Test
    public void testDeleteAssigneeByRequest() {
        final boolean result = assigneeService.deleteAssignee(1L);
        Mockito.verify(assigneeRepository, Mockito.times(1)).deleteById(1L);
        assertTrue(result);
    }

    @Test
    public void testAssigneeExists() {
        AssigneeRequest assigneeRequest = AssigneeRequest.builder().assignee_id(1L).assignee_name("test").assignee_surname("test").build();
        final boolean result = assigneeService.deleteAssignee(assigneeRequest);
        Mockito.verify(assigneeRepository, Mockito.times(1)).deleteById(assigneeRequest.getAssignee_id());
        assertTrue(result);
    }

    @Test
    public void testGetAssignee() {
        Assignee actualAssignee = assigneeService.getAssignee(1L);
        Assignee expectedAssignee = Assignee.builder().id(1L).assigneeName("test").assigneeSurname("test").build();
        assertEquals(expectedAssignee, actualAssignee);
    }

}
