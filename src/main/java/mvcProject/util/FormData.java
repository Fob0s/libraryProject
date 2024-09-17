package mvcProject.util;

import mvcProject.model.Book;
import mvcProject.model.LibraryReader;

public class FormData {
    private  LibraryReader libraryReader;
    private Book book;

    public LibraryReader getLibraryReader() {
        return libraryReader;
    }

    public void setLibraryReader(LibraryReader libraryReader) {
        this.libraryReader = libraryReader;
    }

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
