package com.example.spring_boot_notes.business;

import com.example.spring_boot_notes.data.Note;
import com.example.spring_boot_notes.data.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {

    @Autowired
    private NoteRepository repo;

    public List<Note> listAll() {
        return repo.findAll();
    }

    public void save(Note note) {
        repo.save(note);
    }

    public Note get(long id) {
        return repo.findById(id);
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}
