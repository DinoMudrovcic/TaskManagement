package com.dinomudrovcic.taskmanagement.controller;

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
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT
)
@TestPropertySource(properties = {"spring.config.location=classpath:/dev.yml"})
@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class AssigneeControllerIntegrationTests {

    @LocalServerPort
    private int port;

    private String baseUrl = "http://localhost";

    @Autowired
    private static TestRestTemplate restTemplate = null;

    @BeforeAll
    public static void init() {
        restTemplate = new TestRestTemplate();
    }

    @BeforeEach
    public void setUp() {
        baseUrl = baseUrl.concat(":").concat(port + "").concat("/api/assignee");
    }

    @Test
    @Sql(statements = "insert into assignee (id, assignee_name, assignee_surname) values (1, 'test', 'test')",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(statements = "delete from assignee",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
    )
    public void testAllAssignees() {
        AssigneeResponse expectedAssigneeResponse = AssigneeResponse.builder().id(1L).assigneeName("test").assigneeSurname("test").build();
        AssigneeResponse[] actualAssigneeResponses = restTemplate.getForObject(baseUrl.concat("/assignees"), AssigneeResponse[].class);
        assertEquals(expectedAssigneeResponse, actualAssigneeResponses[0]);
    }

    @Test
    @Sql(statements = "insert into assignee (id, assignee_name, assignee_surname) values (1, 'test', 'test')",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(statements = "delete from assignee",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
    )
    public void testGetAssigneeByRequest() {
        AssigneeResponse expectedAssigneeResponse = AssigneeResponse.builder().id(1L).assigneeName("test").assigneeSurname("test").build();
        AssigneeRequest request = AssigneeRequest.builder().assignee_id(1L).assignee_name("test").assignee_surname("test").build();
        AssigneeResponse actualAssigneeResponse = restTemplate.postForObject(baseUrl.concat("/fetch"), request, AssigneeResponse.class);
        assertEquals(expectedAssigneeResponse,  actualAssigneeResponse);
    }

    @Test
    @Sql(statements = "insert into assignee (id, assignee_name, assignee_surname) values (1, 'test', 'test')",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(statements = "delete from assignee",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
    )
    public void testGetAssigneeById() {
        AssigneeResponse expectedAssigneeResponse = AssigneeResponse.builder().id(1L).assigneeName("test").assigneeSurname("test").build();
        AssigneeResponse actualAssigneeResponse = restTemplate.getForObject(baseUrl.concat("/{id}"), AssigneeResponse.class, 1L);
        assertEquals(expectedAssigneeResponse,  actualAssigneeResponse);
    }

    @Test
    @Sql(statements = "delete from assignee",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
    )
    public void testSave() {
        AssigneeRequest request = AssigneeRequest.builder().assignee_id(1L).assignee_name("test").assignee_surname("test").build();
        AssigneeResponse expectedAssigneeResponse = AssigneeResponse.builder().id(1L).assigneeName("test").assigneeSurname("test").build();
        AssigneeResponse actualAssigneeResponse = restTemplate.postForObject(baseUrl, request, AssigneeResponse.class);

        assertEquals(expectedAssigneeResponse, actualAssigneeResponse);
    }

    @Test
    @Sql(statements = "insert into assignee (id, assignee_name, assignee_surname) values (1, 'test', 'test')",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(statements = "delete from assignee",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
    )
    public void testUpdate() {
        AssigneeRequest assigneeRequest = AssigneeRequest.builder().assignee_id(1L).assignee_name("test1").assignee_surname("test1").build();
        AssigneeResponse expectedAssigneeResponse = AssigneeResponse.builder().id(1L).assigneeName("test1").assigneeSurname("test1").build();

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<AssigneeRequest> request = new HttpEntity<>(assigneeRequest, httpHeaders);

        HttpEntity<AssigneeResponse> response = restTemplate.exchange(baseUrl, HttpMethod.PUT, request, AssigneeResponse.class);
        AssigneeResponse actualAssigneeResponse = response.getBody();

        assertEquals(expectedAssigneeResponse, actualAssigneeResponse);
    }

    @Test
    @Sql(statements = "insert into assignee (id, assignee_name, assignee_surname) values (1, 'test', 'test')",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(statements = "delete from assignee",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
    )
    public void testDelete() {
        AssigneeRequest assigneeRequest = AssigneeRequest.builder().assignee_id(1L).assignee_name("test").assignee_surname("test").build();

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<AssigneeRequest> request = new HttpEntity<>(assigneeRequest, httpHeaders);

        ResponseEntity<Object> response = restTemplate.exchange(baseUrl, HttpMethod.DELETE, request, Object.class);

        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

    @Test
    @Sql(statements = "insert into assignee (id, assignee_name, assignee_surname) values (1, 'test', 'test')",
            executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD
    )
    @Sql(statements = "delete from assignee",
            executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD
    )
    public void testDeleteById() {
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity request = new HttpEntity(httpHeaders);

        ResponseEntity<Object> response = restTemplate.exchange(baseUrl.concat("/{id}"), HttpMethod.DELETE, request, Object.class, 1L);

        assertTrue(response.getStatusCode().equals(HttpStatus.OK));
    }

}
