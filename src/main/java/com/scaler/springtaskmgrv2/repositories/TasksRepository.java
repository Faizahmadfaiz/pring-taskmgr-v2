package com.scaler.springtaskmgrv2.repositories;

import com.scaler.springtaskmgrv2.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.scheduling.config.Task;

import java.util.Date;
import java.util.List;

public interface TasksRepository extends JpaRepository<TaskEntity, Integer> {
    List<TaskEntity> findAllByCompleted(boolean completed);

    @Query("SELECT t from tasks t WHERE t.completed = false AND t.dueDate < CURRENT_DATE")
    List<TaskEntity> findAllOverdue();

    List<TaskEntity> findAllByCompletedAndDueDateBefore(Boolean completed, Date dueDate )
    List<TaskEntity> findAllByTitleContainingIgnoreCase(String titleFragment);
}
