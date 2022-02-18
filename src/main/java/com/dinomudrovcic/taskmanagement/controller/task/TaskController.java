package com.dinomudrovcic.taskmanagement.controller.task;

import com.dinomudrovcic.taskmanagement.model.request.TaskRequest;
import com.dinomudrovcic.taskmanagement.model.response.TaskResponse;
import com.dinomudrovcic.taskmanagement.service.TaskService;
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
        return taskService.getAllTasks();
    }

    @GetMapping
    public TaskResponse getTask(@Valid @RequestBody TaskRequest request) {
        return taskService.getTask(request);
    }

    @GetMapping("/{id}")
    public TaskResponse getTaskById(@Valid @PathVariable Long id) {
        return taskService.getTaskById(id);
    }

    @PostMapping
    public TaskResponse save(@Valid @RequestBody TaskRequest request) {
        return taskService.saveTask(request);
    }

    @PutMapping
    public TaskResponse update(@Valid @RequestBody TaskRequest request) {
        return taskService.updateTask(request);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@Valid @RequestBody TaskRequest request) {
        return taskService.deleteTask(request) ?
            new ResponseEntity<>(HttpStatus.OK) :
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@Valid @PathVariable Long id) {
        return taskService.deleteTaskById(id) ?
            new ResponseEntity<>(HttpStatus.OK) :
            new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/")
    public TaskResponse assign(@RequestParam("assigneeId") Long assigneeId, @RequestParam("taskId") Long taskId){
        return taskService.taskAssign(assigneeId, taskId);
    }

}
