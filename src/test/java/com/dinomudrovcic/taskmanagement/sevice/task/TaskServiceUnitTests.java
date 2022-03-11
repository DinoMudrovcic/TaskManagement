package com.dinomudrovcic.taskmanagement.sevice.task;

import com.dinomudrovcic.taskmanagement.domain.task.Task;
import com.dinomudrovcic.taskmanagement.domain.task.TaskGroup;
import com.dinomudrovcic.taskmanagement.domain.task.TaskStatus;
import com.dinomudrovcic.taskmanagement.model.task.request.TaskRequest;
import com.dinomudrovcic.taskmanagement.model.task.response.TaskResponse;
import com.dinomudrovcic.taskmanagement.repository.task.SubtaskRepository;
import com.dinomudrovcic.taskmanagement.repository.task.TaskRepository;
import com.dinomudrovcic.taskmanagement.service.task.TaskGroupService;
import com.dinomudrovcic.taskmanagement.service.task.impl.TaskServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TaskServiceUnitTests {

    private TaskGroup taskGroup;

    private Task task;

    @Mock
    private static TaskRepository taskRepository;

    @Mock
    private static SubtaskRepository subtaskRepository;

    @Mock
    private static TaskGroupService taskGroupService;

    @InjectMocks
    private TaskServiceImpl taskService;

    @BeforeEach
    public void init() {

        taskGroup = TaskGroup.builder()
                .id(1L)
                .taskGroupName("testGroup")
                .build();

        task = Task.builder()
                .id(1L)
                .name("test")
                .description("test")
                .taskStatus(TaskStatus.CREATED)
                .taskGroup(taskGroup)
                .build();

        when(taskRepository.getById(anyLong())).thenReturn(task);
        when(taskRepository.getById(any())).thenReturn(null);
        when(taskRepository.save(any())).thenReturn(task);


        when(taskGroupService.getGroupById(any())).thenReturn(taskGroup);

    }

    @Test
    public void testGetAllTasksResponseExists() {
        when(taskRepository.findAll()).thenReturn(Collections.singletonList(task));
        List<TaskResponse> tasksResponse = taskService.getAllTasksResponse();
        assertTrue(!tasksResponse.isEmpty());
    }

    @Test
    public void testGetAllTasksResponseNotExists() {
        when(taskRepository.findAll()).thenReturn(Collections.emptyList());
        List<TaskResponse> tasksResponse = taskService.getAllTasksResponse();
        assertTrue(tasksResponse.isEmpty());
    }

    @Test
    public void testGetTaskByIdWhenExists() {
        when(taskRepository.getById(anyLong())).thenReturn(task);
        TaskResponse taskResponse = taskService.getTaskResponse(1L);
        assertNotNull(taskResponse);
    }

    @Test
    public void testGetTaskByIdWhenNotExists() {
        when(taskRepository.getById(anyLong())).thenReturn(null);
        TaskResponse taskResponse = taskService.getTaskResponse(1L);
        assertEquals(TaskResponse.builder().build(), taskResponse);
    }

    @Test
    public void testSaveTaskResponseExists() {
        when(taskRepository.getById(any())).thenReturn(task);
        taskService.saveTaskResponse(TaskRequest.builder().build());
        verify(taskRepository, never()).save(any());
    }

    @Test
    public void testSaveTaskResponseNotExists() {
        when(taskRepository.getById(any())).thenReturn(null);
        taskService.saveTaskResponse(TaskRequest.builder().build());
        verify(taskRepository).save(any());
    }

    @Test
    public void testUpdateTaskResponseExists() {
        when(taskRepository.getById(any())).thenReturn(task);
        taskService.updateTaskResponse(TaskRequest.builder().build());
        verify(taskRepository).save(any());
    }

    @Test
    public void testUpdateTaskResponseNotExists() {
        when(taskRepository.getById(any())).thenReturn(null);
        taskService.updateTaskResponse(TaskRequest.builder().build());
        verify(taskRepository, never()).save(any());
    }

    @Test
    public void testDeleteTaskByIdWhenExists() {
        when(subtaskRepository.findAllByTaskId(anyLong())).thenReturn(Collections.emptyList());
        when(taskRepository.getById(anyLong())).thenReturn(task);
        taskService.deleteTask(1L);
        verify(taskRepository).deleteById(anyLong());
    }

    @Test
    public void testDeleteTaskByIdWhenNotExists() {
        when(subtaskRepository.findAllByTaskId(anyLong())).thenReturn(Collections.emptyList());
        when(taskRepository.getById(anyLong())).thenReturn(null);
        taskService.deleteTask(1L);
        verify(taskRepository, never()).deleteById(anyLong());
    }

    @Test
    public void testDeleteTaskWhenExists() {
        when(subtaskRepository.findAllByTaskId(anyLong())).thenReturn(Collections.emptyList());
        when(taskRepository.getById(anyLong())).thenReturn(task);
        taskService.deleteTask(TaskRequest.builder().taskId(1L).build());
        verify(taskRepository).deleteById(any());
    }

    @Test
    public void testDeleteTaskWhenNotExists() {
        when(subtaskRepository.findAllByTaskId(anyLong())).thenReturn(Collections.emptyList());
        when(taskRepository.getById(anyLong())).thenReturn(null);
        taskService.deleteTask(TaskRequest.builder().taskId(1L).build());
        verify(taskRepository, never()).deleteById(anyLong());
    }

}
