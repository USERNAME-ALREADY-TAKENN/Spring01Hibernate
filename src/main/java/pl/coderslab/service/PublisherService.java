package pl.coderslab.service;

import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;

import java.util.List;

public interface PublisherService {

    public void save(Publisher publisher);
    public Publisher findOneById(Long id);
    public void delete (Publisher publisher);
    public Publisher findOneByIdWithBooks(Long id);
    public Publisher findOneByName(String name);
    List<Publisher> findAll();
}
