package com.scaler.springtaskmgrv2.services;

import com.scaler.springtaskmgrv2.entities.TaskEntity;
import com.scaler.springtaskmgrv2.repositories.NotesRepository;
import com.scaler.springtaskmgrv2.repositories.TasksRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@Getter
@Setter
public class TasksService {
    final TasksRepository tasksRepository;
    final NotesRepository notesRepository;

    public TasksService(TasksRepository tasksRepository, NotesRepository notesRepository) {
        this.tasksRepository = tasksRepository;
        this.notesRepository = notesRepository;
    }

    public List<TaskEntity> getTasks() {
        return tasksRepository.findAll();
    }

    public TaskEntity createTask(String title, String description, Boolean completed, String dueDate) {
//        var task = new TaskEntity();
//        task.setTitle(title);
//        task.setDescription(description);
//        task.setCompleted(completed);
//        task.setDueDate(dueDate);
        TaskEntity task = new TaskEntity();
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setCompleted(false);
        var savedTask = tasksRepository.save(task);
        return savedTask;
    }
}
