package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.entity.Publisher;
import pl.coderslab.service.PublisherService;

import javax.transaction.Transactional;


@Controller
@RequestMapping("/publisher")
public class PublisherController {

    private PublisherService publisherService;

    @Autowired
    public PublisherController(PublisherService publisherService) {
        this.publisherService = publisherService;
    }
    @RequestMapping("/add/{name}")
    @ResponseBody
    public String savePublisher(@PathVariable String name) {
        Publisher publisher = new Publisher();
        publisher.setName(name);
        publisherService.save(publisher);
        return "saved at id: "+publisher.getId();
    }

}