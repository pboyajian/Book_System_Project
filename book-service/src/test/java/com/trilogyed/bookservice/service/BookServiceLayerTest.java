package com.trilogyed.bookservice.service;

import com.trilogyed.bookservice.dao.BookRepository;
import com.trilogyed.bookservice.dto.Book;
import com.trilogyed.bookservice.util.feign.NoteServerClient;
import com.trilogyed.bookservice.util.messages.Note;
import com.trilogyed.bookservice.viewmodel.BookViewModel;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


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

    @Test
    public void shouldCreateGetGetAll(){
        BookViewModel bvm = new BookViewModel();
        bvm.setAuthor("Dickens");
        bvm.setTitle("Tale of Two Cities");
        List<Note> notes = new ArrayList<>();
        Note note2 = new Note();
        note2.setBookId(1);
        note2.setNote("Test");
        note2.setNoteId(1);
        notes.add(note2);
        bvm.setNoteList(notes);
        BookViewModel addedBvm=serviceLayer.addBook(bvm);
        bvm.setBookId(1);
        //tests book creation/addition
        assertEquals(bvm,addedBvm);
        //test get
        assertEquals(bvm,serviceLayer.findBook(1));
        //test get all
        List<BookViewModel> bookViewModelList = new ArrayList<>();
        bookViewModelList.add(bvm);
        assertEquals(bookViewModelList,serviceLayer.findAllBooks());
    }
    @Test
    public void shouldBuildBookViewModel(){
        BookViewModel bvm = new BookViewModel();
        bvm.setAuthor("Dickens");
        bvm.setTitle("Tale of Two Cities");
        bvm.setBookId(1);

        Book book2 = new Book();
        book2.setBookId(1);
        book2.setAuthor("Dickens");
        book2.setTitle("Tale of Two Cities");

        Note note2 = new Note();
        note2.setBookId(1);
        note2.setNote("Test");
        note2.setNoteId(1);

        List<Note> notes = new ArrayList<>();
        notes.add(note2);
        bvm.setNoteList(notes);

        assertEquals(bvm,serviceLayer.buildBookViewModel(book2));



    }

    private void setUpNoteServerClientMock() {
        Note note = new Note();
        note.setBookId(1);
        note.setNote("Test");

        Note note2 = new Note();
        note2.setBookId(1);
        note2.setNote("Test");
        note2.setNoteId(1);

        List<Note> noteList = new ArrayList<>();
        noteList.add(note2);

        when(noteServerClient.addNote(note)).thenReturn(note2);
        when(noteServerClient.getNote(1)).thenReturn(note2);
        when(noteServerClient.getAllNote()).thenReturn(noteList);
        when(noteServerClient.getAllNotesByBookId(1)).thenReturn(noteList);
    }

    private void setUpBookRepoMock(){

        Book book = new Book();
        book.setAuthor("Dickens");
        book.setTitle("Tale of Two Cities");

        Book book2 = new Book();
        book2.setBookId(1);
        book2.setAuthor("Dickens");
        book2.setTitle("Tale of Two Cities");

        List<Book> bookList = new ArrayList<>();
        bookList.add(book2);

        doReturn(Optional.of(book2)).when(bookRepository).findById(1);
        doReturn(bookList).when(bookRepository).findAll();
        doReturn(Optional.of(book2)).when(bookRepository).save(book);

    }
}