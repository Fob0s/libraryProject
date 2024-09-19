package mvcProject.controllers;

import jakarta.validation.Valid;
import mvcProject.DAO.BookDAO;
import mvcProject.DAO.LibraryReaderDAO;
import mvcProject.util.FormData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;


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

    @GetMapping("/return")
    public String returnBook(Model model) {
        model.addAttribute("bookInReader", bookDAO.getAllBookInReader());
        model.addAttribute("formData", new FormData());
        return "admin/returnBook";
    }

    @PostMapping("/returning")
    public String returning(@ModelAttribute("formData") FormData formData) {
        System.out.println(formData.getBook());
        System.out.println(formData.getReader());
        bookDAO.removeReader(formData.getBook());
        return "/admin/start";
    }

    @PostMapping("/submit")
    public String getingBook(@ModelAttribute("formData") @Valid FormData formData,
                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "admin/getBook";
        }
        bookDAO.setReader(formData.getReader(), formData.getBook());
        return "redirect:/admin/succesGetBook";
    }

    @GetMapping("/succesGetBook")
    public String succesGetBook() {
        return "admin/SuccesGetBook";
    }
}
