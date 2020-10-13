package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import javax.persistence.Query;

@Repository
@Transactional
public class BookDao {

    @PersistenceContext
    private EntityManager em;

    public void save(Book book) {
        if(book.getId() == null) this.em.persist(book);
        else this.em.merge(book);
    }
    public Book findOneById(Long id) {
        return this.em.find(Book.class, id);
    }
    public Book findOneByName(String name) {
        return this.em.find(Book.class, name);
    }
    public void delete(Book book) {
        this.em.remove(this.em.contains(book) ? book : this.em.merge(book));
    }

    public List<Book> findAll(){
        Query query = this.em.createQuery("Select b from Book b join fetch b.publisher join fetch b.authors Order by b.title asc");
        List<Book> books = query.getResultList();
        return books;
    }

    public List<Book> getRatingList(int rating) {
        Query query = this.em.createQuery("Select b from Book b where b.rating = :rating");
        query.setParameter("rating", rating);
        List<Book> books = query.getResultList();
        return books;
    }

    //Uzupełnij klasę BookDao o metodę do pobierania listy wszystkich książek, które mają jakiegokolwiek wydawcę.
    public List<Book> findAllWithPublisher() {
        Query query = this.em.createQuery("Select b from Book b Join fetch b.publisher Order by b.title asc");
        List<Book> books = query.getResultList();
        return books;
    }

    //Uzupełnij klasę BookDao o metodę do pobierania listy wszystkich książek, które mają określonego w parametrze wydawcę.
    public List<Book> findAllWithThisPublisher(Publisher publisher) {
        Query query = this.em.createQuery("Select b from Book b join fetch b.publisher join fetch b.authors where b.publisher = :publisher Order by b.title asc");
        query.setParameter("publisher", publisher);
        List<Book> books = query.getResultList();
        return books;
    }

    //Uzupełnij klasę BookDao o metodę do pobierania listy wszystkich książek, które mają określonego w parametrze autora.
    public List<Book> findAllWithThisAuthor(Author author) {
        Query query = this.em.createQuery("Select b from Book b join fetch b.authors where b.authors = :author Order by b.title asc");
        query.setParameter("author", author);
        List<Book> books = query.getResultList();
        return books;
    }
}
//    public Publisher findOneByIdWithBooks(Long id) {
//        Publisher publisher = publisherDao.findOneById(id);
//        Hibernate.initialize(publisher.getBooks());
//        return publisher;