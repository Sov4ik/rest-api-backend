package springjwt.service;

import org.springframework.transaction.annotation.Transactional;
import springjwt.models.Book;
import springjwt.repository.BookRepository;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public List<Book> findAll(){
        return bookRepository.findAll();
    }

    @Transactional
    public ResponseEntity<Book> getBookById(Long bookId){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + bookId));
        return ResponseEntity.ok().body(book);
    }

    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Transactional
    public ResponseEntity<Book> updateBook(Long bookId,
                                                   Book bookDetails){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + bookId));

        book.setId(bookDetails.getId());
        book.setName(bookDetails.getName());
        book.setDescription(bookDetails.getDescription());
        final Book updatedEmployee = bookRepository.save(book);

        return ResponseEntity.ok(updatedEmployee);
    }


    @Transactional
    public Map<String, Boolean> deleteBook(@PathVariable(value = "id") Long bookId)
        throws ResourceNotFoundException {
            Book book = bookRepository.findById(bookId)
                    .orElseThrow(() -> new ResourceNotFoundException("Book not found for this id :: " + bookId));

            bookRepository.delete(book);
            Map<String, Boolean> response = new HashMap<>();
            response.put("deleted", Boolean.TRUE);
            return response;
    }
}
