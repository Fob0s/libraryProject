package mvcProject.controllers;

import jakarta.validation.Valid;
import mvcProject.DAO.LibraryReaderDAO;
import mvcProject.model.LibraryReader;
import mvcProject.util.LibraryReaderValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/readers")
public class ReaderController {
    private final LibraryReaderDAO libraryReaderDAO;
    private final LibraryReaderValidator libraryReaderValidator;
    @Autowired
    public ReaderController(LibraryReaderDAO libraryReaderDAO, LibraryReaderValidator libraryReaderValidator) {
        this.libraryReaderDAO = libraryReaderDAO;
        this.libraryReaderValidator = libraryReaderValidator;
    }
    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("allReaders", libraryReaderDAO.showAll());
        return "readers/readers";
    }
    @GetMapping("/{id}")
    public String readerById(@PathVariable ("id") int id, Model model) {
        model.addAttribute("reader", libraryReaderDAO.readerById(id));
        return "readers/readersById";
    }
    @GetMapping("/new")
    public String newReader(@ModelAttribute("newReader") LibraryReader libraryReader) {
        return "readers/newReader";
    }
    @PostMapping()
    public String createPerson(@ModelAttribute("newReader") @Valid LibraryReader libraryReader,
                               BindingResult bindingResult) {
        libraryReaderValidator.validate(libraryReader, bindingResult);
        if (bindingResult.hasErrors()) {
            return "readers/newReader";
        }
        libraryReaderDAO.save(libraryReader);
        return "redirect:/readers";
    }
    @GetMapping("{id}/edit")
    public String updateReader(Model model, @PathVariable("id") int id) {
        model.addAttribute("reader", libraryReaderDAO.readerById(id));
        return "readers/edit";
    }
    @PatchMapping("/{id}")
    public String editReader(@ModelAttribute("reader") @Valid LibraryReader libraryReader,
                             BindingResult bindingResult, @PathVariable("id") int id) {
        if (bindingResult.hasErrors()) {
            return "readers/edit";
        }
        libraryReaderDAO.update(libraryReader, id);
        return "redirect:/readers";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        libraryReaderDAO.delete(id);
        return "redirect:/readers";
    }
}
