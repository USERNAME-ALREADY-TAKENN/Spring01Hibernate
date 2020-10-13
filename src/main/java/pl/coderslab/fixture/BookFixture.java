package pl.coderslab.fixture;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;
import pl.coderslab.service.BookService;
import pl.coderslab.service.PublisherService;

import java.util.Random;

@Component
public class BookFixture {
    private BookService bookService;
    private PublisherService publisherService;
    private Faker faker;

    @Autowired
    public BookFixture(BookService bookService, PublisherService publisherService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.faker = new Faker();
    }

    public void createDataInDb() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setTitle(faker.book().title());
            book.setRating(random.nextInt(10)+1);
            book.setDescription(this.faker.lorem().fixedString(100)+"...");

            long publisherId = (long) (random.nextInt(3)+1);
            Publisher publisher = publisherService.findOneById(publisherId);
            book.setPublisher(publisher);
            bookService.save(book);
        }
    }
}
