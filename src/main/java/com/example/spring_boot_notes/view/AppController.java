package com.example.spring_boot_notes.view;

import com.example.spring_boot_notes.business.NoteService;
import com.example.spring_boot_notes.data.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Controller
public class AppController {

    @Autowired
    private NoteService service;
    //Set the date format for the note time stamps
    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");

    @RequestMapping("/")
    public String viewHomePage(Model model) {
        //List all of our notes on the homepage
        List<Note> listNotes = service.listAll();
        model.addAttribute("listNotes", listNotes);

        return "index";
    }

    @RequestMapping("/new")
    public String showNewNotePage(Model model) {
        //create a new note
        Note note = new Note();
        model.addAttribute("note", note);

        return "new_note";
    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String saveNote(@ModelAttribute("note") Note note) {
        //Save a note, whether new or edited. A POST request is sent to the database
        LocalDateTime now = LocalDateTime.now();
        note.setDate(dtf.format(now));
        service.save(note);

        return "redirect:/";
    }

    @RequestMapping("/edit/{id}")
    public ModelAndView showEditNotePage (@PathVariable(name = "id") int id ) {
        //Edit a note by showing the edit note page, get the note from the data by id
        ModelAndView mav = new ModelAndView("edit_note");
        Note note = service.get(id);
        mav.addObject("note", note);

        return mav;
    }

    @RequestMapping("/delete/{id}")
    public String deleteNote(@PathVariable(name = "id") int id) {
        //Delete a note from the database by id
        service.delete(id);
        return "redirect/";
    }
}
