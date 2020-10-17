package pl.coderslab.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;

import java.util.List;
import java.util.Set;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query("select b from Book b join fetch b.publisher p join fetch b.authors a")
    Set<Book> findAllWithAllData();
    List<Book> findAllByAuthors(Author author);
    List<Book> findAllByAuthorsFirstName(String firstName);
    @Query("select b from Book b where b.title = :title")
    List<Book> findAllByTitle(@Param("title") String title);
    @Query("Select b from Book b join b.publisher p join fetch b.authors a where p.name = :name")
    Set<Book> findAllByPublisherName(@Param("name") String name);

}
