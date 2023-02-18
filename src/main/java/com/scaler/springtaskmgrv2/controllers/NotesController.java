package com.scaler.springtaskmgrv2.controllers;

import com.scaler.springtaskmgrv2.entities.NoteEntity;
import com.scaler.springtaskmgrv2.services.NotesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks/{taskId}/notes")
public class NotesController {
    final NotesService notesService;

    public NotesController(NotesService notesService) {
        this.notesService = notesService;
    }

    @PostMapping("")
    public ResponseEntity<NoteEntity> addNote(
            @PathVariable("taskId") Integer taskId,
            @RequestBody NoteEntity noteEntity
    ) {
      var note = notesService.addNoteForTask(taskId, noteEntity);
      return ResponseEntity.ok(note);
    }

    @GetMapping("")
    public ResponseEntity<List<NoteEntity>> getNotes(@PathVariable("taskId") Integer taskId) {
        var notes = notesService.getNotesForTask(taskId);
        return ResponseEntity.ok(notes);
    }

    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> deleteNote(
            @PathVariable("taskId") Integer taskId,
            @PathVariable("noteId") Integer noteId
    ) {
        notesService.deleteNote(taskId, noteId);
        return ResponseEntity.noContent().build();
    }
}
