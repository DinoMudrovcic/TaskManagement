package com.dinomudrovcic.taskmanagement.controller.task;

import com.dinomudrovcic.taskmanagement.model.TaskRequest;
import com.dinomudrovcic.taskmanagement.model.TaskResponse;
import com.dinomudrovcic.taskmanagement.service.TaskService;
import lombok.RequiredArgsConstructor;
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

    @GetMapping("/{name}")
    public TaskResponse getTaskByName(@Valid @PathVariable String name) {
        return taskService.getTaskByName(name);
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
    public TaskResponse delete(@Valid @RequestBody TaskRequest request) {
        return taskService.deleteTask(request);
    }

    @DeleteMapping("/{id}")
    public TaskResponse deleteById(@Valid @PathVariable Long id) {
        return taskService.deleteTaskById(id);
    }

}
