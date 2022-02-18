package com.dinomudrovcic.taskmanagement.controller.assignee;

import com.dinomudrovcic.taskmanagement.model.request.AssigneeRequest;
import com.dinomudrovcic.taskmanagement.model.response.AssigneeResponse;
import com.dinomudrovcic.taskmanagement.service.AssigneeService;
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
        return assigneeService.getAllAssignees();
    }

    @GetMapping
    public AssigneeResponse getAssignee(@Valid @RequestBody AssigneeRequest request) {
        return assigneeService.getAssignee(request);
    }

    @GetMapping("/{id}")
    public AssigneeResponse getAssigneeById(@Valid @PathVariable Long id) {
        return assigneeService.getAssigneeById(id);
    }

    @PostMapping
    public AssigneeResponse save(@Valid @RequestBody AssigneeRequest request) {
        return assigneeService.saveAssignee(request);
    }

    @PutMapping
    public AssigneeResponse update(@Valid @RequestBody AssigneeRequest request) {
        return assigneeService.updateAssignee(request);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@Valid @RequestBody AssigneeRequest request) {
        return assigneeService.deleteAssignee(request) ?
            new ResponseEntity<>(HttpStatus.OK) :
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@Valid @PathVariable Long id) {
        return assigneeService.deleteAssignee(id) ?
            new ResponseEntity<>(HttpStatus.OK) :
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
