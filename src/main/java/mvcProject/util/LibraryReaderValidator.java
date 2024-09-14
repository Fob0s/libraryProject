package mvcProject.util;

import mvcProject.DAO.LibraryReaderDAO;
import mvcProject.model.LibraryReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

@Component
public class LibraryReaderValidator implements Validator {

    private final LibraryReaderDAO libraryReaderDAO;
    @Autowired
    public LibraryReaderValidator(LibraryReaderDAO libraryReaderDAO) {
        this.libraryReaderDAO = libraryReaderDAO;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return LibraryReader.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        LibraryReader reader = (LibraryReader) target;

        if (libraryReaderDAO.uniqueSurnameValidate(reader.getSurname()).isPresent()) {
            errors.rejectValue("surname", "code-1", "Surname is resorved");
        }
    }
}
