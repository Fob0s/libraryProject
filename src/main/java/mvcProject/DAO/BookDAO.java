package mvcProject.DAO;

import mvcProject.model.Book;
import mvcProject.mappers.ReadersBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {


    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> showAll() {
        return jdbcTemplate.query("select * from mvc_schema.books", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book showBookById(int id) {
        return jdbcTemplate.query("select * from mvc_schema.books where id = ?",
                new BeanPropertyRowMapper<>(Book.class), id).stream().findAny().orElse(null);
    }

    public void createBook(Book newBook) {
        jdbcTemplate.update("insert into mvc_schema.books (name, author, yearofpublication) values (?, ?, ?)",
                newBook.getName(), newBook.getAuthor(), newBook.getYearOfPublication());
    }

    public void updateBook(Book updateBook, int id) {
        jdbcTemplate.update("update mvc_schema.books set name = ?, author = ?, yearofpublication = ? where id = ?",
                updateBook.getName(), updateBook.getAuthor(), updateBook.getYearOfPublication(), id);
    }

    public void deleteBook(int id) {
        jdbcTemplate.update("delete from mvc_schema.books where id = ?", id);
    }


    public void setReader(int idReader, int idBook) {
        jdbcTemplate.update("update mvc_schema.books set id_reader = ? where id = ?", idReader, idBook);
    }

    public List<ReadersBook> getAllBookInReader() {
        return jdbcTemplate.query("select book.id as idBook, book.name as nameBook, reader.name as nameReader," +
                " reader.surname as surnameReader from mvc_schema.readers as reader inner join mvc_schema.books " +
                "as book on reader.id = book.id_reader", new BeanPropertyRowMapper<>(ReadersBook.class));
    }

    public void removeReader(int id) {
        jdbcTemplate.update("update mvc_schema.books set id_reader = ? where id = ?", null, id);
    }

    public int countAllBook() {
        return jdbcTemplate.query("select * from mvc_schema.books", new BeanPropertyRowMapper<>(Book.class)).size();
    }

    public List<Book> freeBook() {
        return jdbcTemplate.query("select * from mvc_schema.books where id_reader is null",
                new BeanPropertyRowMapper<>(Book.class));
    }

    public int countFreeBook() {
        return freeBook().size();
    }

    public int countBusyBook() {
        return jdbcTemplate.query("select * from mvc_schema.books where id_reader notnull",
                new BeanPropertyRowMapper<>(Book.class)).size();
    }


}
