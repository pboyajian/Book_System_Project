package com.trilogyed.noteservice.controller;

import com.trilogyed.noteservice.dao.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class NoteController {

    @Autowired
    private NoteRepository noteRepo;
}
