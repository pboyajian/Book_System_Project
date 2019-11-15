package com.trilogyed.noteservice.controller;

import com.trilogyed.noteservice.dao.NoteRepository;
import com.trilogyed.noteservice.dto.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController {

    @Autowired
    private NoteRepository noteRepo;
    @RequestMapping(value = "/note", method = RequestMethod.POST)
    Note addNote(@RequestBody Note note){
        return noteRepo.save(note);
    }

    @RequestMapping(value = "/note/{id}", method = RequestMethod.GET)
    Note getNote(@PathVariable int id){
        return noteRepo.getOne(id);
    }

    @RequestMapping(value = "/note", method = RequestMethod.GET)
    List<Note> getAllNote(){
        return noteRepo.findAll();
    }

    @RequestMapping(value = "/note", method = RequestMethod.PUT)
    void updateNote(@RequestBody Note note){
        noteRepo.save(note);
    }

    @RequestMapping(value = "/note/{id}", method = RequestMethod.DELETE)
    void deleteNote(@PathVariable int id){
        noteRepo.deleteById(id);
    }

    @RequestMapping(value = "/note/book/{bookId}", method = RequestMethod.GET)
    List<Note> getAllNotesByBookId(@PathVariable int bookId){
        return noteRepo.findByBookId(bookId);
    }
}
