package com.trilogyed.bookservice.service;

import com.trilogyed.bookservice.dao.BookRepository;
import com.trilogyed.bookservice.dto.Book;
import com.trilogyed.bookservice.util.feign.NoteServerClient;
import com.trilogyed.bookservice.viewmodel.BookViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookServiceLayer {

    private BookRepository bookRepository;

    private NoteServerClient noteServerClient;

    public BookServiceLayer(BookRepository bookRepository, NoteServerClient noteServerClient) {
        this.bookRepository = bookRepository;
        this.noteServerClient = noteServerClient;
    }

    public BookViewModel addBook(BookViewModel bvm) {
        Book book=new Book();
        book.setAuthor(bvm.getAuthor());
        book.setTitle(bvm.getTitle());
        book=bookRepository.save(book);
        bvm.getNoteList().forEach(
                x -> noteServerClient.addNote(x)
        );
        bvm.setBookId(book.getBookId());
        return bvm;
    }
    public BookViewModel findBook(int id){
        return null;
    }

    public List<BookViewModel> findAllBooks() {return null;
    }

    public BookViewModel buildBookViewModel(Book book) {
        BookViewModel bvm=new BookViewModel();
        bvm.setBookId(book.getBookId());
        bvm.setTitle(book.getTitle());
        bvm.setAuthor(book.getAuthor());
        bvm.setNoteList(noteServerClient.getAllNotesByBookId(book.getBookId()));
        return bvm;
    }
}
