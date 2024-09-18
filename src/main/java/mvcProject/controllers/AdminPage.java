package mvcProject.controllers;

import jakarta.servlet.http.HttpServletRequest;
import mvcProject.DAO.BookDAO;
import mvcProject.DAO.LibraryReaderDAO;
import mvcProject.model.Book;
import mvcProject.model.LibraryReader;
import mvcProject.util.FormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminPage {
    private final BookDAO bookDAO;
    private final LibraryReaderDAO libraryReaderDAO;

    @Autowired
    public AdminPage(BookDAO bookDAO, LibraryReaderDAO libraryReaderDAO) {
        this.bookDAO = bookDAO;
        this.libraryReaderDAO = libraryReaderDAO;
    }
    @GetMapping()
    public String startPage() {
        return "admin/start";
    }
    @GetMapping("/get")
    public String getBook(Model model) {
        model.addAttribute("formData", new FormData());
        model.addAttribute("allReaders", libraryReaderDAO.showAll());
        model.addAttribute("freeBook", bookDAO.freeBook());
        return "admin/getBook";
    }

    @PostMapping("/submit")
    public String getingBook(@ModelAttribute("formData") FormData formData) {
        System.out.println("Formdata: "+formData.getReader()+" "+formData.getBook());
        System.out.println("run post map");
        LibraryReader libraryReader = libraryReaderDAO.readerById(formData.getReader());
        Book book = bookDAO.showBookById(formData.getBook());
        System.out.printf("Id: %d, fullName: %s%n", libraryReader.getId(), libraryReader.getFullName());
        System.out.printf("Name: %s%n", book.getName());
        bookDAO.setReader(libraryReader.getId(), book.getId());
        return "redirect:/admin";
    }
}
