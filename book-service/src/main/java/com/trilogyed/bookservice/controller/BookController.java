package com.trilogyed.bookservice.controller;

import com.trilogyed.bookservice.dao.BookRepository;
import com.trilogyed.bookservice.service.BookServiceLayer;
import com.trilogyed.bookservice.viewmodel.BookViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookServiceLayer service;

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public BookViewModel createBook(@RequestBody BookViewModel bvm) {
       return service.addBook(bvm);

    }
}

