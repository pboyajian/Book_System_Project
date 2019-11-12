package com.trilogyed.noteservice.dao;

import com.trilogyed.noteservice.dto.Note;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends JpaRepository<Note, Integer> {
    public List<Note>findByBookId(int bookId);
}

