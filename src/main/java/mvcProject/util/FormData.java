package mvcProject.util;

import jakarta.validation.constraints.Positive;

public class FormData {
    @Positive(message = "Do not selected reader")
    private int reader;

    @Positive(message = "Do not selected book")
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
