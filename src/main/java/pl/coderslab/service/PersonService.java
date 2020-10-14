package pl.coderslab.service;

import pl.coderslab.entity.Person;

import java.util.List;

public interface PersonService {
    public void save(Person person);
    public Person findOneById(long id);
    public List<Person> findAll();
    public void delete(Person person);
}
