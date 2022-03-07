package com.dinomudrovcic.taskmanagement.controller.task;

import com.dinomudrovcic.taskmanagement.model.task.request.TaskGroupRequest;
import com.dinomudrovcic.taskmanagement.model.task.response.TaskGroupResponse;
import com.dinomudrovcic.taskmanagement.service.task.TaskGroupService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/taskgroup")
@RequiredArgsConstructor
public class TaskGroupController {

    private final TaskGroupService taskGroupService;

    @GetMapping("/groups")
    public List<TaskGroupResponse> getAllGroups() {
        return taskGroupService.getAllTaskGroups();
    }

    @GetMapping
    public TaskGroupResponse getTask(@Valid @RequestBody final TaskGroupRequest request) {
        return taskGroupService.getTaskGroup(request);
    }

    @GetMapping("/{id}")
    public TaskGroupResponse getTaskById(@Valid @PathVariable final Long id) {
        return taskGroupService.getTaskGroup(id);
    }

    @PostMapping
    public TaskGroupResponse save(@Valid @RequestBody final TaskGroupRequest request) {
        return taskGroupService.saveTaskGroup(request);
    }

    @PutMapping
    public TaskGroupResponse update(@Valid @RequestBody final TaskGroupRequest request) {
        return taskGroupService.updateTaskGroup(request);
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@Valid @RequestBody final TaskGroupRequest request) {
        return taskGroupService.deleteTaskGroup(request) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteById(@Valid @PathVariable final Long id) {
        return taskGroupService.deleteTaskGroup(id) ?
                new ResponseEntity<>(HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

}
