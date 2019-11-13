package com.trilogyed.bookservice.viewmodel;

import com.trilogyed.bookservice.util.messages.Note;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

public class BookViewModel {
    private int bookId;
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @NotEmpty(message = "Author cannot be empty")
    private String author;
    private List<Note> noteList;

    public BookViewModel() {
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BookViewModel that = (BookViewModel) o;
        return bookId == that.bookId &&
                Objects.equals(title, that.title) &&
                Objects.equals(author, that.author) &&
                Objects.equals(noteList, that.noteList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookId, title, author, noteList);
    }

    @Override
    public String toString() {
        return "BookViewModel{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", noteList=" + noteList +
                '}';
    }
}
