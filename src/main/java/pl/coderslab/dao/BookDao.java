package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Book;

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
    public void delete(Book book) {
        this.em.remove(this.em.contains(book) ? book : this.em.merge(book));
    }

    public List<Book> findAll(){
        Query query = this.em.createQuery("Select b from Book b Order by b.title asc");
        List<Book> books = query.getResultList();
        return books;
    }

    public List<Book> findAllWithPublisher() {
        Query query = this.em.createQuery("Select b from Book b Join fetch b.publisher Order by b.title asc");
        List<Book> books = query.getResultList();
        return books;
    }

    public List<Book> getRatingList(int rating) {
        Query query = this.em.createQuery("Select b from Book b where b.rating = :rating");
        query.setParameter("rating", rating);
        List<Book> books = query.getResultList();
        return books;
    }
}