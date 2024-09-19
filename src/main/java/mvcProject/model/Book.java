package mvcProject.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;


public class Book {
    private int id;
    @NotEmpty(message = "The book name must not be empty")
    private String name;
    @NotEmpty(message = "The book author must not be empty")
    private String author;

    @Min(value = 0, message = "gg")
    private int yearOfPublication;

    private Integer id_reader;


    public Book() {
    }

    public Book(String name, String author, int yearOfPublication, Integer id_reader) {
        this.name = name;
        this.author = author;
        this.yearOfPublication = yearOfPublication;
        this.id_reader = id_reader;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }
    public void setYearOfPublication(int yearOfPublication) {
        this.yearOfPublication = yearOfPublication;
    }


    public Integer getId_reader() {
        if (id_reader == null){
            return 0;
        }else {
            return id_reader;
        }
    }

    public void setId_reader(Integer id_reader) {
        this.id_reader = id_reader;
    }

    public boolean getHand() {
        if (getId_reader() > 0) {
            return true;
        }else {
            return false;
        }
    }
}
