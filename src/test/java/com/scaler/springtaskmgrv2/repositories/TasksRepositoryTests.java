package com.scaler.springtaskmgrv2.repositories;

import com.scaler.springtaskmgrv2.entities.TaskEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.springframework.test.util.AssertionErrors.assertEquals;
import static org.springframework.test.util.AssertionErrors.assertNotNull;


@DataJpaTest
public class TasksRepositoryTests {

    @Autowired
    TasksRepository tasksRepository;

    @Test
    public void testCreateTask() {
        TaskEntity task = new TaskEntity();
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setCompleted(false);
        var savedTask = tasksRepository.save(task);
        assertNotNull("task is created", savedTask);
    }

    @Test
    public void readTaskWorks() {
        TaskEntity task1 = new TaskEntity();
        task1.setTitle("Test Task");
        task1.setDescription("Test Description");
        task1.setCompleted(false);
        TaskEntity task2 = new TaskEntity();
        task2.setTitle("Test Task2");
        task2.setDescription("Test Description2");
        task2.setCompleted(false);
        tasksRepository.save(task1);
        tasksRepository.save(task2);
        var tasks = tasksRepository.findAll();
        assertNotNull("2 tasks created", tasks);
        assertEquals("2 tasks got created", 2, tasks.size());
    }

    @Test
    public void findByCompletedWorks() {
        TaskEntity task1 = new TaskEntity();
        task1.setTitle("Test Task");
        task1.setDescription("Test Description");
        task1.setCompleted(false);
        TaskEntity task2 = new TaskEntity();
        task2.setTitle("Test Task2");
        task2.setDescription("Test Description2");
        task2.setCompleted(true );
        tasksRepository.save(task1);
        tasksRepository.save(task2);
        var completedTasks = tasksRepository.findAllByCompleted(true);
        var inCompletedTasks = tasksRepository.findAllByCompleted(false);
        assertEquals("1 completed task saved", 1, completedTasks.size());
        assertEquals("1 uncompleted tasks saved", 1, inCompletedTasks.size());
    }
}
