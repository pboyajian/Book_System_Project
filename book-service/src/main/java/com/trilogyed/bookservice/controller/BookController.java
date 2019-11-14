package com.trilogyed.bookservice.controller;

import com.trilogyed.bookservice.dao.BookRepository;
import com.trilogyed.bookservice.dto.Book;
import com.trilogyed.bookservice.util.messages.Note;
import com.trilogyed.bookservice.viewmodel.BookViewModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    @Autowired
    private BookRepository bookRepository;
    public static final String EXCHANGE = "note-exchange";
    public static final String ROUTING_KEY = "note.#";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public BookController(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public String createBook(@RequestBody BookViewModel bvm) {
        // create message to send to note creation queue
        bvm.getNoteList().forEach(note -> {
            System.out.println("Sending message...");
            rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, note);
            System.out.println("Message Sent");
        });
        // Now do book creation stuff...
        Book book=new Book();
        book.setAuthor(bvm.getAuthor());
        book.setTitle(bvm.getTitle());
        book=bookRepository.save(book);


        return "Book Created";
    }
}

