package springjwt.repository;

import org.springframework.stereotype.Repository;
import springjwt.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin("http://localhost:4200")
@Repository
public interface BookRepository extends JpaRepository<Book, Long>{

}
