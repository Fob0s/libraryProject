package mvcProject.controllers;

import jakarta.validation.Valid;
import mvcProject.DAO.BookDAO;
import mvcProject.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/books")
public class BooksController {

    private final BookDAO bookDAO;
    @Autowired
    public BooksController(BookDAO bookDAO) {
        this.bookDAO = bookDAO;
    }

    @GetMapping()
    public String showAll(Model model) {
        model.addAttribute("books", bookDAO.showAll());
        model.addAttribute("allBooks", bookDAO.countAllBook());
        model.addAttribute("busyBook", bookDAO.countBusyBook());
        model.addAttribute("freeBook", bookDAO.countFreeBook());
        return "books/books";
    }

    @GetMapping("/{id}")
    public String bookById(@PathVariable("id") int id, Model model) {
        model.addAttribute("book", bookDAO.showBookById(id));
        return "books/bookById";
    }
    @GetMapping("/new")
    public String newBook(@ModelAttribute("newBook") Book book) {
        System.out.println("run new book");
        return "books/newBook";
    }
    @PostMapping()
    public String createBook(@ModelAttribute("newBook") @Valid Book book,
                               BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "books/newBook";
        }
        bookDAO.createBook(book);
        return "redirect:/books";
    }
    @GetMapping("{id}/edit")
    public String updateBook(Model model, @PathVariable("id") int id) {
        model.addAttribute("book", bookDAO.showBookById(id));
        return "books/edit";
    }
    @PatchMapping("/{id}")
    public String editBook(@ModelAttribute("book") @Valid Book book,
                             BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "books/edit";
        }
        bookDAO.updateBook(book, id);
        return "redirect:/books";
    }

    @DeleteMapping("/{id}")
    public String deleteBook(@PathVariable("id") int id) {
        bookDAO.deleteBook(id);
        return "redirect:/books";
    }
}
