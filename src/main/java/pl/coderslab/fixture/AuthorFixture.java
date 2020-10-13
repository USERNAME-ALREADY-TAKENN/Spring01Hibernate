package pl.coderslab.fixture;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;
import pl.coderslab.service.AuthorService;
import pl.coderslab.service.BookService;
import pl.coderslab.service.PublisherService;

import java.util.Random;

@Component
public class AuthorFixture {
    private AuthorService authorService;
    private Faker faker;

    @Autowired
    public AuthorFixture(AuthorService authorService) {
        this.authorService = authorService;
        this.faker = new Faker();
    }

    public void createDataInDb() {
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Author author = new Author();
            author.setFirstName(faker.name().firstName());
            author.setLastName(faker.name().lastName());
            authorService.save(author);
        }
    }
}
