package springjwt.controllers;

import org.springframework.security.access.prepost.PreAuthorize;
import springjwt.models.Book;
import springjwt.service.BookService;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }

    @GetMapping("/books")
    public List<Book> getAllBook() {
        return bookService.findAll();
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<Book> getEmployeeById(@PathVariable(value = "id") Long bookId)
            throws ResourceNotFoundException {

        return bookService.getBookById(bookId);
    }

    @PostMapping("/books")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean createBook(@Valid @RequestBody Book book) {
        bookService.save(book);
        return true;
    }

    @PutMapping("/books/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean updateBook(@PathVariable(value = "id") Long bookId,
                                                   @Valid @RequestBody Book bookDetails) throws ResourceNotFoundException {
        bookService.updateBook(bookId, bookDetails);
        return true;
    }

    @DeleteMapping("/books/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public boolean deleteBook(@PathVariable(value = "id") Long bookId){
        bookService.deleteBook(bookId);
        return true;
    }
}
