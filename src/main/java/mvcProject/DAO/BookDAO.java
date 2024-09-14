package mvcProject.DAO;

import mvcProject.model.Book;
import mvcProject.model.LibraryReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

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

    public void setReader(LibraryReader reader, int id) {
        jdbcTemplate.update("update mvc_schema.books set id_reader = ? where id = ?", reader.getId(), id);
    }

    public void removeReader(int id) {
        jdbcTemplate.update("update mvc_schema.books set id_reader = ? where id = ?", null, id);
    }
}
