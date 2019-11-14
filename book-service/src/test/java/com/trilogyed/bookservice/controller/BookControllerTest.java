package com.trilogyed.bookservice.controller;

import com.trilogyed.bookservice.service.BookServiceLayer;
import com.trilogyed.bookservice.util.feign.NoteServerClient;
import com.trilogyed.bookservice.util.messages.Note;
import com.trilogyed.bookservice.viewmodel.BookViewModel;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.autoconfigure.RefreshAutoConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@WebMvcTest(BookController.class)
@ImportAutoConfiguration(RefreshAutoConfiguration.class)
public class BookControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private NoteServerClient noteServerClient;

    @MockBean
    private BookServiceLayer serviceLayer;

    private static final int BOOK_VIEW_MODEL_ID_THAT_EXISTS = 1;
    private static final int BOOK_ID_THAT_EXISTS =1;
    private static final String AUTHOR = "Dickens";
    private static final String TITLE = "Tale of Two Cities";
    private static final Note NOTE = new Note(1,"Test");
    private BookViewModel bookViewModel;


    @Before
    public void setUp() throws Exception {
        List<Note> noteList = new ArrayList<>();
        noteList.add(NOTE);
        bookViewModel = new BookViewModel();
        bookViewModel.setAuthor(AUTHOR);
        bookViewModel.setTitle(TITLE);
        bookViewModel.setNoteList(noteList);

    }

//    @Test
//    public void shouldAddBook(){
//        BookViewModel bookViewModelToAdd = new BookViewModel();
//        bookViewModelToAdd.setAuthor(AUTHOR);
//        bookViewModelToAdd.setTitle(TITLE);
//        bookViewModelToAdd.setNoteList(noteList);
//        when(serviceLayer.addBook(bookViewModel)).thenReturn(bookViewModel);
//
//
//    }


}