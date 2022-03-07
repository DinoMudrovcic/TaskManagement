package com.dinomudrovcic.taskmanagement.controller.assignee;

import com.dinomudrovcic.taskmanagement.model.assignee.request.AssigneeRequest;
import com.dinomudrovcic.taskmanagement.model.assignee.response.AssigneeResponse;
import com.dinomudrovcic.taskmanagement.service.assignee.AssigneeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/assignee")
@RequiredArgsConstructor
public class AssigneeController {

    private final AssigneeService assigneeService;

    @GetMapping("/assignees")
    public List<AssigneeResponse> getAllAssignees() {
        return assigneeService.getAllAssigneesResponse();
    }

    @PostMapping("/fetch")
    public AssigneeResponse getAssigneeByRequest(@Valid @RequestBody final AssigneeRequest request) {
        return assigneeService.getAssigneeResponse(request);
    }

    @GetMapping("/{id}")
    public AssigneeResponse getAssigneeById(@Valid @PathVariable final Long id) {
        return assigneeService.getAssigneeResponse(id);
    }

    @PostMapping
    public AssigneeResponse save(@Valid @RequestBody final AssigneeRequest request) {
        return assigneeService.saveAssigneeResponse(request);
    }

    @PutMapping
    public AssigneeResponse update(@Valid @RequestBody final AssigneeRequest request) {
        return assigneeService.updateAssigneeResponse(request);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@Valid @RequestBody final AssigneeRequest request) {
        return assigneeService.deleteAssignee(request) ?
            new ResponseEntity<>(HttpStatus.OK) :
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@Valid @PathVariable final Long id) {
        return assigneeService.deleteAssignee(id) ?
            new ResponseEntity<>(HttpStatus.OK) :
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
