package com.trilogyed.bookservice.service;

import com.trilogyed.bookservice.dao.BookRepository;
import com.trilogyed.bookservice.dto.Book;
import com.trilogyed.bookservice.util.feign.NoteServerClient;
import com.trilogyed.bookservice.util.messages.Note;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest
public class BookServiceLayerTest {

    private BookRepository bookRepository;
    private NoteServerClient noteServerClient;
    private BookServiceLayer serviceLayer;



    @Before
    public void setUp() throws Exception {
        bookRepository = mock(BookRepository.class);
        noteServerClient = mock(NoteServerClient.class);

        serviceLayer = new BookServiceLayer(bookRepository, noteServerClient);

    setUpBookRepoMock();
    setUpNoteServerClientMock();
    }

    private void setUpNoteServerClientMock() {
        Note note = new Note();
        note.setBookId(1);
        note.setNote("Test");

        Note note2 = new Note();
        note.setBookId(1);
        note.setNote("Test");
        note.setNoteId(1);

        List<Note> noteList = new ArrayList<>();
        noteList.add(note2);

        when(noteServerClient.addNote(note)).thenReturn(note2);
        when(noteServerClient.getNote(1)).thenReturn(note2);
        when(noteServerClient.getAllNote()).thenReturn(noteList);
        when(noteServerClient.getAllNotesByBookId(1)).thenReturn(noteList);
    }

    public void setUpBookRepoMock(){

        Book book = new Book();
        book.setAuthor("Dickens");
        book.setTitle("Tale of Two Cities");

        Book book2 = new Book();
        book2.setBookId(1);
        book2.setAuthor("Dickens");
        book2.setTitle("Tale of Two Cities");

        List<Book> bookList = new ArrayList<>();
        bookList.add(book2);

        doReturn(book2).when(bookRepository).findById(1);
        doReturn(bookList).when(bookRepository).findAll();
        doReturn(book2).when(bookRepository).save(book);

    }


}