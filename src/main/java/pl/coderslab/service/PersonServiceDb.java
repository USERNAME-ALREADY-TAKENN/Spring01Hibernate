package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.coderslab.dao.PersonDao;
import pl.coderslab.entity.Person;

import java.util.List;

@Service
public class PersonServiceDb implements PersonService {
    private PersonDao personDao;

    @Autowired
    public PersonServiceDb(PersonDao personDao) {
        this.personDao = personDao;
    }

    @Override
    public void save(Person person) {
        personDao.save(person);
    }

    @Override
    public Person findOneById(long id) {
        return personDao.findOneById(id);
    }

    @Override
    public List<Person> findAll() {
        return personDao.findAll();
    }

    @Override
    public void delete(Person person) {
        personDao.delete(person);
    }
}
