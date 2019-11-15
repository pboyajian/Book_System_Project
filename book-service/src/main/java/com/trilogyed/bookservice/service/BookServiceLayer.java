package com.trilogyed.bookservice.service;

import com.trilogyed.bookservice.dao.BookRepository;
import com.trilogyed.bookservice.dto.Book;
import com.trilogyed.bookservice.util.feign.NoteServerClient;
import com.trilogyed.bookservice.util.messages.Note;
import com.trilogyed.bookservice.viewmodel.BookViewModel;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookServiceLayer {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private NoteServerClient noteServerClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public static final String EXCHANGE = "note-exchange";
    public static final String ROUTING_KEY = "note.#";

    public BookServiceLayer() {
    }

    public BookServiceLayer(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public BookServiceLayer(BookRepository bookRepository, NoteServerClient noteServerClient) {
        this.bookRepository = bookRepository;
        this.noteServerClient = noteServerClient;
    }

    public BookViewModel addBook(BookViewModel bvm) {
        Book book=new Book();
        book.setAuthor(bvm.getAuthor());
        book.setTitle(bvm.getTitle());
        book=bookRepository.save(book);
        bvm.setBookId(book.getBookId());
        bvm.getNoteList().forEach(
                x ->{x.setBookId(bvm.getBookId());
                    System.out.println("Sending message...");
                    rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, x);
                    System.out.println("Message Sent");}
        );
        bvm.setNoteList(noteServerClient.getAllNotesByBookId(bvm.getBookId()));
        return bvm;
    }
    public BookViewModel findBook(int id){
        return buildBookViewModel(bookRepository.getOne(id));
    }

    public List<BookViewModel> findAllBooks() {
        List<BookViewModel> bookViewModelList = new ArrayList<>();
        bookRepository.findAll().forEach(book->bookViewModelList.add(buildBookViewModel(book)));
        return bookViewModelList;
    }

    public BookViewModel buildBookViewModel(Book book) {
        BookViewModel bvm=new BookViewModel();
        bvm.setBookId(book.getBookId());
        bvm.setTitle(book.getTitle());
        bvm.setAuthor(book.getAuthor());
        bvm.setNoteList(noteServerClient.getAllNotesByBookId(book.getBookId()));
        return bvm;
    }
    public void updateBookViewModel(BookViewModel bvm){
        Book book=new Book();
        book.setAuthor(bvm.getAuthor());
        book.setTitle(bvm.getTitle());
        book=bookRepository.save(book);
        bvm.getNoteList().forEach(
                x -> {System.out.println("Sending message...");
                rabbitTemplate.convertAndSend(EXCHANGE, ROUTING_KEY, x);
                System.out.println("Message Sent");}
        );
    }
    public void deleteBookViewModel(int id){
        BookViewModel bvm = findBook(id);
        bvm.getNoteList().forEach(
                x -> noteServerClient.deleteNote(x.getNoteId())
        );
        bookRepository.deleteById(bvm.getBookId());
    }

    public List<Note> getAllNotesByBookId(int bookId) {
        return noteServerClient.getAllNotesByBookId(bookId);
    }
}
