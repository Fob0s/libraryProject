package mvcProject.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public class LibraryReader {
    private int id;
    @NotEmpty(message = "The name must not be empty")
    @Size(min = 2, max = 30, message = "The name must be between 2 and 30 characters long")
    private String name;
    @NotEmpty(message = "The surname must not be empty")
    @Size(min = 2, max = 30, message = "The surname must be between 2 and 30 characters long")
    private String surname;

    @NotEmpty(message = "The patronymic must not be empty")
    @Size(min = 2, max = 30, message = "The patronymic must be between 2 and 30 characters long")
    private String patronymic;

    @Min(value = 1, message = "Age must be between 1 and 100")
    @Max(value = 100, message = "Age must be between 1 and 100" )
    private int age;
    private List<Book> books;

    public LibraryReader() {
    }

    public LibraryReader(String name, String surname, String patronymic, int age, List<Book> books) {
        this.name = name;
        this.surname = surname;
        this.patronymic = patronymic;
        this.age = age;
        this.books = books;
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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public String getFullName() {
        return String.format("%s %s %s", surname, name, patronymic);
    }

}
