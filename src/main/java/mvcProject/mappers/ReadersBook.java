package mvcProject.mappers;

public class ReadersBook {
    private int idBook;
    private String nameBook;
    private String nameReader;
    private String surnameReader;

    public ReadersBook() {
    }

    public ReadersBook(int idBook, String nameBook, String nameReader, String surnameReader) {
        this.idBook = idBook;
        this.nameBook = nameBook;
        this.nameReader = nameReader;
        this.surnameReader = surnameReader;
    }

    public int getIdBook() {
        return idBook;
    }

    public void setIdBook(int idBook) {
        this.idBook = idBook;
    }

    public String getNameBook() {
        return nameBook;
    }

    public void setNameBook(String nameBook) {
        this.nameBook = nameBook;
    }

    public String getNameReader() {
        return nameReader;
    }

    public void setNameReader(String nameReader) {
        this.nameReader = nameReader;
    }

    public String getSurnameReader() {
        return surnameReader;
    }

    public void setSurnameReader(String surnameReader) {
        this.surnameReader = surnameReader;
    }

    public String getNameAndSurname() {
        return String.format("%s %s", this.nameReader, this.surnameReader);
    }
}
