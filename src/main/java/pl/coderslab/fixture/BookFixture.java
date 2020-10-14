package pl.coderslab.fixture;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;
import pl.coderslab.service.AuthorService;
import pl.coderslab.service.BookService;
import pl.coderslab.service.PublisherService;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class BookFixture {
    private BookService bookService;
    private PublisherService publisherService;
    private AuthorService authorService;
    private Faker faker;

    @Autowired
    public BookFixture(BookService bookService, PublisherService publisherService, AuthorService authorService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.authorService = authorService;
        this.faker = new Faker();
    }

    public void createDataInDb() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Book book = new Book();
            book.setTitle(faker.book().title());
            book.setRating(random.nextInt(10)+1);
            book.setDescription(this.faker.lorem().fixedString(100)+"...");
            book.setPages(this.faker.number().numberBetween(50,1000));

            long publisherId = (long) (random.nextInt(3)+1);
            Publisher publisher = publisherService.findOneById(publisherId);
            book.setPublisher(publisher);

            //get authors, add to list
            List<Author> authors = new ArrayList<>();
            int numOfAuthors = random.nextInt(4)+1;
            if(numOfAuthors == 4) numOfAuthors = 1;
            for (int j = 0; j < numOfAuthors; j++) {
                long authorId = (long) (random.nextInt(10)+1);
                Author author = authorService.findOneById(authorId);
                authors.add(author);
            }
            book.setAuthors(authors);
            bookService.save(book);
        }
    }
}
