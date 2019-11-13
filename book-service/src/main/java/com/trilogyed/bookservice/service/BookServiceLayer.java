package com.trilogyed.bookservice.service;

import com.trilogyed.bookservice.dao.BookRepository;
import com.trilogyed.bookservice.util.feign.NoteServerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookServiceLayer {

    private BookRepository bookRepository;

    private NoteServerClient noteServerClient;

    public BookServiceLayer(BookRepository bookRepository, NoteServerClient noteServerClient) {
        this.bookRepository = bookRepository;
        this.noteServerClient = noteServerClient;
    }
}
