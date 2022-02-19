package com.dinomudrovcic.taskmanagement.controller.task;

import com.dinomudrovcic.taskmanagement.model.response.TaskResponse;
import com.dinomudrovcic.taskmanagement.service.TaskFilterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/filter/task")
@RequiredArgsConstructor
public class TaskFilterController {

    private final TaskFilterService service;

    @GetMapping
    public List<TaskResponse> getAllByName(@RequestParam("name") final String name) {
        return service.getAllByNameLike(name);
    }

    @GetMapping
    public List<TaskResponse> getAllByDescription(@RequestParam("description") final String description) {
        return service.getAllByDescriptionLike(description);
    }

    @GetMapping
    public List<TaskResponse> getAllByTaskStatus(@RequestParam("status") final String status) {
        return service.getAllByTaskStatus(status);
    }

    @GetMapping
    public List<TaskResponse> getAllByDurationGreater(@RequestParam("duration") final Long duration) {
        return service.getAllByDurationGreaterThanEqual(duration);
    }

    @GetMapping
    public List<TaskResponse> getAllByDurationLess(@RequestParam("duration") final Long duration) {
        return service.getAllByDurationLessThanEqual(duration);
    }

    @GetMapping
    public List<TaskResponse> getAllByTotalDurationGreater(@RequestParam("duration") final Long duration) {
        return service.getAllByTotalDurationGreaterThanEqual(duration);
    }

    @GetMapping
    public List<TaskResponse> getAllByTotalDurationLess(@RequestParam("duration") final Long duration) {
        return service.getAllByTotalDurationLessThanEqual(duration);
    }
}

