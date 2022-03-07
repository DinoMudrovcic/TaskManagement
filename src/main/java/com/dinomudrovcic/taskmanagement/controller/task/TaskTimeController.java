package com.dinomudrovcic.taskmanagement.controller.task;

import com.dinomudrovcic.taskmanagement.service.task.TaskTimeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/task/time")
@RequiredArgsConstructor
public class TaskTimeController {

    private final TaskTimeService taskTimeService;


    @PostMapping("/assign")
    public ResponseEntity<?> assignTask(@RequestParam("assigneeId") final Long assigneeId, @RequestParam("taskId") final Long taskId){
        return taskTimeService.taskAssign(assigneeId, taskId) ?
                new ResponseEntity(HttpStatus.ACCEPTED) :
                new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/assign/subtask")
    public ResponseEntity<?> assignSubTask(@RequestParam("assigneeId") final Long assigneeId, @RequestParam("subtaskId") final Long subtaskId){
        return taskTimeService.subTaskAssign(assigneeId, subtaskId) ?
                new ResponseEntity(HttpStatus.ACCEPTED) :
                new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/unassign")
    public ResponseEntity<?> unassignTask(@RequestParam("taskId") final Long taskId){
        return taskTimeService.taskUnassign(taskId) ?
                new ResponseEntity(HttpStatus.ACCEPTED) :
                new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/unassign/task")
    public ResponseEntity<?> unassignSubTask(@RequestParam("subtaskId") final Long subtaskId){
        return taskTimeService.subTaskUnassign(subtaskId) ?
                new ResponseEntity(HttpStatus.ACCEPTED) :
                new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/done")
    public ResponseEntity<?> doneWorkingOnTask(@RequestParam("taskId") final Long taskId) {
        return taskTimeService.endTaskTime(taskId) ?
                new ResponseEntity(HttpStatus.ACCEPTED) :
                new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

    @PostMapping("/done/subtask")
    public ResponseEntity<?> doneWorkingOnSubTask(@RequestParam("subtaskId") final Long subtaskId) {
        return taskTimeService.endSubTaskTime(subtaskId) ?
                new ResponseEntity(HttpStatus.ACCEPTED) :
                new ResponseEntity(HttpStatus.NOT_ACCEPTABLE);
    }

}
