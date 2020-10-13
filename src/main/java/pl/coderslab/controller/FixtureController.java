package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.entity.Author;
import pl.coderslab.fixture.AuthorFixture;
import pl.coderslab.fixture.BookFixture;
import pl.coderslab.fixture.PublisherFixture;

@Controller
@RequestMapping("/fixtures")
public class FixtureController {
    private PublisherFixture publisherFixture;
    private BookFixture bookFixture;
    private AuthorFixture authorFixture;

    @Autowired
    public FixtureController(PublisherFixture publisherFixture, BookFixture bookFixture, AuthorFixture authorFixture) {
        this.publisherFixture = publisherFixture;
        this.bookFixture = bookFixture;
        this.authorFixture = authorFixture;
    }

    @GetMapping
    @ResponseBody
    public String loadDataToDb() {
        publisherFixture.createDataInDb();
        authorFixture.createDataInDb();
        bookFixture.createDataInDb();
        return "data created";
    }

}
