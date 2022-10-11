package com.example.spring_boot_notes.data;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class NoteRepository {

    private List<Note> notes = new ArrayList<Note>();

    public List<Note> findAll() {
        return notes;
    }

    public void save (Note note){
        int index = 0;
        for (Note nt : notes) {
            if (nt.getId().equals(note.getId())) {
                //An existing note is being updated
                notes.set(index, note);
                return;
            }
            index++;
        }
        //Note is not in the list, add it to the end
        notes.add(note);
    }

    public Note findById (long id) {
        for (Note note : notes ) {
            if (note.getId().equals(id)){
                //Note is found in the list
                return note;
            }
        }
        //Note is not in the list
        return null;
    }

    public void deleteById(long id) {
        //Delete a note in the list by id
        for (Note note : notes) {
            if (note.getId().equals(id)){
                //Note is found in the list, remove it
                notes.remove(note);
                return;
            }
        }
    }
}
