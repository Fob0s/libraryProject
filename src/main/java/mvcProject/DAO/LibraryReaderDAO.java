package mvcProject.DAO;

import mvcProject.model.LibraryReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class LibraryReaderDAO {
    private final JdbcTemplate jdbcTemplate;
    @Autowired
    public LibraryReaderDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<LibraryReader> showAll() {
        return jdbcTemplate.query("select * from mvc_schema.readers", new BeanPropertyRowMapper<>(LibraryReader.class));
    }

    public LibraryReader readerById(int id) {
        return jdbcTemplate.query("select * from mvc_schema.readers where id = ?",
                new BeanPropertyRowMapper<>(LibraryReader.class), id).stream().findAny()
                .orElse(null);
    }

    public void save(LibraryReader libraryReader) {
        jdbcTemplate.update("insert into mvc_schema.readers (name, surname, patronymic, age) values (?, ?, ?, ?)",
                libraryReader.getName(), libraryReader.getSurname(), libraryReader.getPatronymic(), libraryReader.getAge());
    }

    public void update(LibraryReader updateLibraryReader, int id) {
        jdbcTemplate.update("update mvc_schema.readers set name = ?, surname = ?, patronymic = ?, age = ? where id = ?",
                updateLibraryReader.getName(), updateLibraryReader.getSurname(), updateLibraryReader.getPatronymic(), updateLibraryReader.getAge(), id);
    }

    public void delete(int id) {
        jdbcTemplate.update("delete from mvc_schema.readers where id = ?", id);
    }

    public Optional<LibraryReader> uniqueSurnameValidate(String surname) {
        return jdbcTemplate.query("select * from mvc_schema.readers where surname = ?",
                new BeanPropertyRowMapper<>(LibraryReader.class), surname).stream().findAny();
    }
}
