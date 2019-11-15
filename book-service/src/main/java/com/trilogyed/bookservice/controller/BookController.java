package com.trilogyed.bookservice.controller;

import com.trilogyed.bookservice.dao.BookRepository;
import com.trilogyed.bookservice.service.BookServiceLayer;
import com.trilogyed.bookservice.util.messages.Note;
import com.trilogyed.bookservice.viewmodel.BookViewModel;
import org.hibernate.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @PutMapping(value = "/book")
    public void updateBook(@RequestBody BookViewModel bvm){
        service.updateBookViewModel(bvm);
    }

    @GetMapping(value ="/book/{id}")
    public BookViewModel getBook(@PathVariable int id){
        return service.findBook(id);
    }

    @GetMapping(value ="/book")
    public List<BookViewModel> getAllBooks(){
        return service.findAllBooks();
    }

    @DeleteMapping(value = "/book/{id}")
    public void deleteBook(@PathVariable int id){
        service.deleteBookViewModel(id);
    }

    @RequestMapping(value = "/note/book/{bookId}", method = RequestMethod.GET)
    List<Note> getAllNotesByBookId(@PathVariable int bookId){
        return service.getAllNotesByBookId(bookId);
    }

}

