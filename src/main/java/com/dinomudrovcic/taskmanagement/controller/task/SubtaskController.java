package com.dinomudrovcic.taskmanagement.controller.task;

import com.dinomudrovcic.taskmanagement.model.request.SubtaskRequest;
import com.dinomudrovcic.taskmanagement.model.response.SubtaskResponse;
import com.dinomudrovcic.taskmanagement.service.SubtaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/subtask")
@RequiredArgsConstructor
public class SubtaskController {

    private final SubtaskService subTaskService;

    @GetMapping("/subtasks")
    public List<SubtaskResponse> getAllTasks() {
        return subTaskService.getAllSubTasksResponse();
    }

    @GetMapping
    public SubtaskResponse getTask(@Valid @RequestBody final SubtaskRequest request) {
        return subTaskService.getSubTaskResponse(request);
    }

    @GetMapping("/{id}")
    public SubtaskResponse getTaskById(@Valid @PathVariable final Long id) {
        return subTaskService.getSubTaskResponse(id);
    }

    @PostMapping
    public SubtaskResponse save(@Valid @RequestBody final SubtaskRequest request) {
        return subTaskService.saveSubTaskResponse(request);
    }

    @PutMapping
    public SubtaskResponse update(@Valid @RequestBody final SubtaskRequest request) {
        return subTaskService.updateSubTaskResponse(request);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@Valid @RequestBody final SubtaskRequest request) {
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
