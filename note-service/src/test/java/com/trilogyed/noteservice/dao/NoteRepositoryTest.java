package com.trilogyed.noteservice.dao;

import com.trilogyed.noteservice.dto.Note;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class NoteRepositoryTest {
private NoteRepository noteRepository;
    @Before
    public void setUp() throws Exception {
        noteRepository.findAll().forEach(x->noteRepository.delete(x));
    }

    public void shouldFindByBookId(){
        Note note=new Note();
        note.setNote("note");
        note.setBookId(1);
        note=noteRepository.save(note);
        List<Note> noteList = new ArrayList<>();
        noteList.add(note);
        assertEquals(noteList,noteRepository.findByBookId(1));
    }
}