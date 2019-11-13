package com.trilogyed.noteservice.controller;

import com.trilogyed.noteservice.dao.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoteController {

    @Autowired
    private NoteRepository noteRepo;
}
