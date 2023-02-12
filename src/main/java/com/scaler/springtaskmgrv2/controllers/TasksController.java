package com.scaler.springtaskmgrv2.controllers;

import com.scaler.springtaskmgrv2.entities.TaskEntity;
import com.scaler.springtaskmgrv2.services.TasksService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

@RestController
public class TasksController {
    final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping("/tasks")
    ResponseEntity<List<TaskEntity>> getTasks() {
        return ResponseEntity.ok(tasksService.getTasks());
    }

    @PostMapping("/tasks")
    ResponseEntity<TaskEntity> createTask(@RequestBody TaskEntity task) {
        var newTask = tasksService.createTask(task.getTitle(), task.getDescription(), task.getCompleted(), task.getDueDate());
        return ResponseEntity.ok(newTask);
    }
}
