package pl.coderslab.service;

import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;

import java.util.List;

public interface BookService {

    public void save(Book book);
    public void delete (Book book);
    public Book findOneById(Long id);
    public Book findOneByName(String title);
    public List<Book> findAll();
    public List<Book> findAllWithPublisher();
    public List<Book> findAllWithThisPublisher(Publisher publisher);
    public List<Book> findAllWithThisAuthor(Author author);
    public List<Book> getRatingList(int rating);

    public Book findOneByIdWithAllData(Long id);

}
