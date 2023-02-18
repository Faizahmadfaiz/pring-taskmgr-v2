package com.scaler.springtaskmgrv2.services;

import com.scaler.springtaskmgrv2.entities.NoteEntity;
import com.scaler.springtaskmgrv2.repositories.NotesRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotesService {
    final private NotesRepository notesRepository;

    final private TasksService tasksService;

    public static class NoteNotFoundException extends IllegalArgumentException {
        public NoteNotFoundException(Integer noteId) {
            super("Note with id " + noteId + " not found");
        }
    }

    public NotesService(NotesRepository notesRepository, TasksService tasksService) {
        this.notesRepository = notesRepository;
        this.tasksService = tasksService;
    }

    public NoteEntity addNoteForTask(Integer taskId, NoteEntity noteEntity) {
        var task = tasksService.getTaskById(taskId);
        noteEntity.setTask(task);
        var savedNote = notesRepository.save(noteEntity);
        return savedNote;
    }

    public List<NoteEntity> getNotesForTask(Integer taskId) {
        var task = tasksService.getTaskById(taskId);
        var notes = notesRepository.findAllByTask(task);
        return notes;
    }

    public void deleteNote(Integer taskId, Integer noteId) {
        var task = tasksService.getTaskById(taskId);

        if (task == null) {
            throw new TasksService.TaskNotFoundException(taskId);
        }

        var note = notesRepository.findById(noteId).get();

        if (note == null) {
            throw new NoteNotFoundException(noteId);
        }

        if (note.getTask().getId().equals(taskId)) {
            notesRepository.deleteById(noteId);
        } else {
            throw new NoteNotFoundException(noteId);
        }

    }
}
