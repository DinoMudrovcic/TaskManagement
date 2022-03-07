package com.dinomudrovcic.taskmanagement.controller.task;

import com.dinomudrovcic.taskmanagement.model.task.response.TaskResponse;
import com.dinomudrovcic.taskmanagement.service.task.TaskFilterService;
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

    @GetMapping("/name")
    public List<TaskResponse> filterByName(@RequestParam("name") final String name) {
        return service.getAllByNameLike(name);
    }

    @GetMapping("/description")
    public List<TaskResponse> filterByDescription(@RequestParam("description") final String description) {
        return service.getAllByDescriptionLike(description);
    }

    @GetMapping("/status")
    public List<TaskResponse> filterByTaskStatus(@RequestParam("status") final String status) {
        return service.getAllByTaskStatus(status);
    }

    @GetMapping("/duration/greater")
    public List<TaskResponse> filterByDurationGreater(@RequestParam("duration") final Long duration) {
        return service.getAllByDurationGreaterThanEqual(duration);
    }

    @GetMapping("/duration/less")
    public List<TaskResponse> filterByDurationLess(@RequestParam("duration") final Long duration) {
        return service.getAllByDurationLessThanEqual(duration);
    }

    @GetMapping("/totalduration/greater")
    public List<TaskResponse> filterByTotalDurationGreater(@RequestParam("duration") final Long duration) {
        return service.getAllByTotalDurationGreaterThanEqual(duration);
    }

    @GetMapping("/totalduration/less")
    public List<TaskResponse> filterByTotalDurationLess(@RequestParam("duration") final Long duration) {
        return service.getAllByTotalDurationLessThanEqual(duration);
    }
}

