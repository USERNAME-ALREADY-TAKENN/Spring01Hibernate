package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Publisher;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public class AuthorDao {

    @PersistenceContext
    private EntityManager em;

    public void save(Author author) {
        if(author.getId() == null) this.em.persist(author);
        else this.em.merge(author);
    }
    public Author findOneById(Long id) {
        return this.em.find(Author.class, id);
    }
    public void delete(Author author) {
        this.em.remove(this.em.contains(author) ? author : this.em.merge(author));
    }

    public List<Author> getAll() {
        Query query = this.em.createQuery("Select a from Author a Order by a.lastName asc");
        List<Author> authors = query.getResultList();
        return authors;
    }
}
