package com.scaler.springtaskmgrv2.repositories;

import com.scaler.springtaskmgrv2.entities.NoteEntity;
import com.scaler.springtaskmgrv2.entities.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NotesRepository extends JpaRepository<NoteEntity, Integer > {
    List<NoteEntity> findAllByTask(TaskEntity task);
}
