package com.scaler.springtaskmgrv2.services;

import com.scaler.springtaskmgrv2.entities.TaskEntity;
import com.scaler.springtaskmgrv2.repositories.NotesRepository;
import com.scaler.springtaskmgrv2.repositories.TasksRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Getter
@Setter
public class TasksService {
    final TasksRepository tasksRepository;
    final NotesRepository notesRepository;

    public static class TaskNotFoundException extends IllegalArgumentException {
        public TaskNotFoundException(Integer taskId) {
            super("Task with id " + taskId + " not found");
        }
    }

    public TasksService(TasksRepository tasksRepository, NotesRepository notesRepository) {
        this.tasksRepository = tasksRepository;
        this.notesRepository = notesRepository;
    }

    public List<TaskEntity> getTasks() {
        return tasksRepository.findAll();
    }

    public TaskEntity createTask(String title, String description, Boolean completed, String dueDate) {
        var task = new TaskEntity();
        task.setTitle(title);
        task.setDescription(description);
        task.setCompleted(completed);
        task.setDueDate(dueDate);
        var savedTask = tasksRepository.save(task);
        return savedTask;
    }

    public TaskEntity getTaskById(Integer id) {
        var task = tasksRepository.findById(id).get();
        return task;
    }

    public List<TaskEntity> getTasksByTitle(String title) {
        var tasks = tasksRepository.findAllByTitle(title);
        return tasks;
    }

    public List<TaskEntity> getTasksByCompleted(Boolean completed) {
        var tasks = tasksRepository.findAllByCompleted(completed);
        return tasks;
    }

    public TaskEntity updateTask(Integer id, String title, String description, Boolean completed, String dueDate) {
        var task = tasksRepository.findById(id).get();
        if (title != null) task.setTitle(title);
        if (description != null) task.setDescription(description);
        if (completed != null) task.setCompleted(completed);
        if (dueDate != null) task.setDueDate(dueDate);
        return tasksRepository.save(task);
    }

    public void deleteTask(Integer id) {
        tasksRepository.deleteById(id);
    }
}
