package mvcProject.util;

import mvcProject.model.Book;
import mvcProject.model.LibraryReader;

public class FormData {
    private int reader;
    private int book;

    public int getReader() {
        return reader;
    }

    public void setReader(int reader) {
        this.reader = reader;
    }

    public int getBook() {
        return book;
    }

    public void setBook(int book) {
        this.book = book;
    }
}
