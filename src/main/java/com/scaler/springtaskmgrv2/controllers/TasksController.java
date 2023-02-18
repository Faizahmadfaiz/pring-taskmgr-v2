package com.scaler.springtaskmgrv2.controllers;

import com.scaler.springtaskmgrv2.entities.TaskEntity;
import com.scaler.springtaskmgrv2.services.TasksService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TasksController {
    final TasksService tasksService;

    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

//    @GetMapping("")
//    ResponseEntity<List<TaskEntity>> getTasks() {
//        return ResponseEntity.ok(tasksService.getTasks());
//    }

    @PostMapping("")
    ResponseEntity<TaskEntity> createTask(@RequestBody TaskEntity task) {
        var newTask = tasksService.createTask(task.getTitle(), task.getDescription(), task.getCompleted(), task.getDueDate());
        return ResponseEntity.created(URI.create("/tasks/" + newTask.getId())).body(newTask);
    }

    @GetMapping("/{id}")
    ResponseEntity<TaskEntity> getTask(@PathVariable("id") Integer id) {
        var task = tasksService.getTaskById(id);
        return ResponseEntity.ok(task);
    }

    @GetMapping("")
    ResponseEntity<List<TaskEntity>> getTasks(
            @RequestParam(required = false, name = "title") String title,
            @RequestParam(required = false, name = "completed") Boolean completed
    ) {
        if (title != null && completed == null) {
            var tasks = tasksService.getTasksByTitle(title);
            return ResponseEntity.ok(tasks);
        }

        if (completed != null && title == null) {
            var tasks = tasksService.getTasksByCompleted(completed);
            return ResponseEntity.ok(tasks);
        }
        return ResponseEntity.ok(tasksService.getTasks());
    }

    @PatchMapping("/tasks/{id}")
    ResponseEntity<TaskEntity> updateTask(@PathVariable("id") Integer id, @RequestBody TaskEntity task) {
        var updatedTask = tasksService.updateTask(id, task.getTitle(), task.getDescription(), task.getCompleted(), task.getDueDate());
        return ResponseEntity.accepted().body(updatedTask);
    }

    @DeleteMapping("/tasks/{id}")
    ResponseEntity<Void> deleteTask(@PathVariable("id") Integer id) {
        tasksService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }
}
