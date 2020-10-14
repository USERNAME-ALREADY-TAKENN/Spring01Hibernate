package pl.coderslab.controller;

import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;
import pl.coderslab.service.PublisherService;

import javax.transaction.Transactional;
import java.util.List;

@Controller
@RequestMapping("/publisher")
public class PublisherController {
    private PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }

    @GetMapping("/add/{name}")
    @ResponseBody
    public String add(@PathVariable String name){
        Publisher publisher = new Publisher();
        publisher.setName(name);
        publisherService.save(publisher);
        return "saved at id: "+publisher.getId();
    }

    @GetMapping("/details/{id}")
    @ResponseBody
    @Transactional
    public String getPublisher(@PathVariable Long id){
        Publisher publisher = publisherService.findOneById(id);

        Hibernate.initialize(publisher.getBooks());

        List<Book> books = publisher.getBooks();

        return books.toString();
    }

}
