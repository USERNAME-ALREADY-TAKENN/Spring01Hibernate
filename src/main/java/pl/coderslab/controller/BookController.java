package pl.coderslab.controller;

import com.github.javafaker.Faker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;
import pl.coderslab.service.BookService;
import pl.coderslab.service.PublisherService;


import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/book")
public class BookController {

    private BookService bookService;
    private PublisherService publisherService;
    private Faker faker;

    @Autowired
    public BookController(BookService bookService, PublisherService publisherService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.faker = new Faker();
    }
    @RequestMapping("/add")
    @ResponseBody
    public String saveBook() {

        Publisher publisher = publisherService.findOneById(1L);

        Book book = new Book();
        book.setTitle(this.faker.superhero().name());
        Random random = new Random();
        book.setRating(random.nextInt(10)+1);
        book.setDescription(this.faker.lorem().fixedString(100)+"...");
        book.setPublisher(publisher);

        this.bookService.save(book);

        return "Book saved at id: "+book.getId();
    }

    @GetMapping()
    public String allBooks(Model model){
        List<Book> bookList = this.bookService.findAllWithPublisher();
        model.addAttribute("books", bookList);
        return "book/list";
    }
    @GetMapping("/rating/{rating}")
    public String allBooks(Model model, @PathVariable int rating){
        List<Book> bookList = this.bookService.getRatingList(rating);
        model.addAttribute("books", bookList);
        return "book/list";
    }





//    @RequestMapping("/update/{id}")
//    @ResponseBody
//    public String updateBook(@PathVariable long id) {
//        Book book = bookService.findOneById(id);
//        book.setTitle("edited title");
//        book.setDescription("edited lorem ipsum");
//        book.setRating(10);
//        bookService.save(book);
//        return "zaaktualizowano: " + book.toString();
//    }
//
//    @RequestMapping("/get/{id}")
//    @ResponseBody
//    @Transactional
//    public String getBook(@PathVariable long id) {
//        Book book = bookService.findOneById(id);
//        return book.toString();
//    }
//    @RequestMapping("/delete/{id}")
//    @ResponseBody
//    public String deleteBook(@PathVariable long id) {
//        Book book = bookService.findOneById(id);
//        bookService.delete(book);
//        return "removed: " + book.toString();
//    }
}