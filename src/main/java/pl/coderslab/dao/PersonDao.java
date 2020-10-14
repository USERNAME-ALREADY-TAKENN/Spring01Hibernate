package pl.coderslab.dao;

import org.springframework.stereotype.Repository;
import pl.coderslab.entity.Person;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.transaction.Transactional;
import java.util.List;


@Repository
@Transactional
public class PersonDao {
    @PersistenceContext
    private EntityManager em;

    public void save(Person person) {
        if(person.getId() == null){
            em.persist(person);
        }else{
            em.merge(person);
        }
    }

    public Person findOneById(long id) {
        return em.find(Person.class, id);
    }

    public List<Person> findAll() {
        Query q = em.createQuery("Select p From Person p order by p.email");
        return (List<Person>) q.getResultList();
    }

    public void delete(Person person) {
        em.remove( em.contains(person) ? person : em.merge(person) );
    }
}
