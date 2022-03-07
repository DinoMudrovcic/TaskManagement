package com.dinomudrovcic.taskmanagement.controller.task;

import com.dinomudrovcic.taskmanagement.model.task.request.TaskRequest;
import com.dinomudrovcic.taskmanagement.model.task.response.TaskResponse;
import com.dinomudrovcic.taskmanagement.service.task.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/task")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping("/tasks")
    public List<TaskResponse> getAllTasks() {
        return taskService.getAllTasksResponse();
    }

    @GetMapping
    public TaskResponse getTask(@Valid @RequestBody final TaskRequest request) {
        return taskService.getTaskResponse(request);
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@Valid @PathVariable final Long id) {
        return taskService.getTaskResponse(id);
    }

    @PostMapping
    public TaskResponse save(@Valid @RequestBody final TaskRequest request) {
        return taskService.saveTaskResponse(request);
    }

    @PutMapping
    public TaskResponse update(@Valid @RequestBody final TaskRequest request) {
        return taskService.updateTaskResponse(request);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@Valid @RequestBody final TaskRequest request) {
        return taskService.deleteTask(request) ?
            new ResponseEntity<>(HttpStatus.OK) :
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@Valid @PathVariable final Long id) {
        return taskService.deleteTask(id) ?
            new ResponseEntity<>(HttpStatus.OK) :
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
