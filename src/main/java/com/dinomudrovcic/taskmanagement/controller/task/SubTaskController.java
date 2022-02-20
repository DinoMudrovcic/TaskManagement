package com.dinomudrovcic.taskmanagement.controller.task;

import com.dinomudrovcic.taskmanagement.model.request.SubTaskRequest;
import com.dinomudrovcic.taskmanagement.model.response.SubTaskResponse;
import com.dinomudrovcic.taskmanagement.service.SubTaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/subtask")
@RequiredArgsConstructor
public class SubTaskController {

    private final SubTaskService subTaskService;

    @GetMapping("/subtasks")
    public List<SubTaskResponse> getAllTasks() {
        return subTaskService.getAllSubTasksResponse();
    }

    @GetMapping
    public SubTaskResponse getTask(@Valid @RequestBody final SubTaskRequest request) {
        return subTaskService.getSubTaskResponse(request);
    }

    @GetMapping("/{id}")
    public SubTaskResponse getTaskById(@Valid @PathVariable final Long id) {
        return subTaskService.getSubTaskResponse(id);
    }

    @PostMapping
    public SubTaskResponse save(@Valid @RequestBody final SubTaskRequest request) {
        return subTaskService.saveSubTaskResponse(request);
    }

    @PutMapping
    public SubTaskResponse update(@Valid @RequestBody final SubTaskRequest request) {
        return subTaskService.updateSubTaskResponse(request);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@Valid @RequestBody final SubTaskRequest request) {
        return subTaskService.deleteSubTask(request) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@Valid @PathVariable final Long id) {
        return subTaskService.deleteSubTask(id) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
