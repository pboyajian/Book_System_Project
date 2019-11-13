package com.trilogyed.bookservice.util.feign;

import com.trilogyed.bookservice.util.messages.Note;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.List;

@FeignClient(name = "book-service")
public interface NoteServerClient {
    @RequestMapping(value = "/note", method = RequestMethod.POST)
     Note addNote(@RequestBody @Valid Note note);

    @RequestMapping(value = "/note/{id}", method = RequestMethod.GET)
     Note getNote(@PathVariable int id);

    @RequestMapping(value = "/note", method = RequestMethod.GET)
     List<Note> getAllNote();

    @RequestMapping(value = "/note", method = RequestMethod.PUT)
     void updateNote(@RequestBody @Valid Note note);

    @RequestMapping(value = "/note/{id}", method = RequestMethod.DELETE)
     void deleteNote(@PathVariable int id);

    @RequestMapping(value = "/note/book/{bookId}", method = RequestMethod.GET)
    List<Note> getAllNotesByBookId(@PathVariable int bookId);
}