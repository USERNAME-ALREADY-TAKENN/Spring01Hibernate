package pl.coderslab.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import pl.coderslab.dao.BookDao;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;
import pl.coderslab.repository.BookRepository;

import java.util.List;

@Service
@Primary
public class BookServiceDb implements BookService {
    private BookDao bookDao;
    private BookRepository bookRepository;

    @Autowired
    public BookServiceDb(BookDao bookDao, BookRepository bookRepository) {
        this.bookDao = bookDao;
        this.bookRepository = bookRepository;
    }

    @Override
    public void save(Book book) {
//        this.bookDao.save(book);
        this.bookRepository.save(book);
    }

    @Override
    public Book findOneById(Long id) {
//        return this.bookDao.findOneById(id);
        return this.bookRepository.findById(id).orElseGet(null);
    }

    @Override
    public void delete(Book book) {
        this.bookDao.delete(book);
    }

    @Override
    public List<Book> findAll() {
        return this.bookDao.findAll();
    }

    @Override
    public List<Book> findAllWithPublisher() {
        return this.bookDao.findAllWithPublisher();
    }

    @Override
    public List<Book> findAllWithThisPublisher(Publisher publisher) {
        return this.bookDao.findAllWithThisPublisher(publisher);
    }

    @Override
    public List<Book> getRatingList(int rating) {
        return this.bookDao.getRatingList(rating);
    }

    @Override
    public List<Book> findAllWithThisAuthor(Author author) {
        return this.bookDao.findAllWithThisAuthor(author);
    }

    @Override
    public Book findOneByName(String title) {
        return bookDao.findOneByName(title);
    }
}
