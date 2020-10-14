package pl.coderslab.controller;

import com.github.javafaker.Faker;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;
import pl.coderslab.service.AuthorService;
import pl.coderslab.service.BookService;
import pl.coderslab.service.PublisherService;


import javax.transaction.Transactional;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("/book")
public class BookController {

    private BookService bookService;
    private PublisherService publisherService;
    private AuthorService authorService;
//    private Faker faker;

    @Autowired
    public BookController(BookService bookService, PublisherService publisherService, AuthorService authorService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.authorService = authorService;
//        this.faker = new Faker();
    }

    @GetMapping("/new")
    public String bookForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "book/form";
    }

    @ModelAttribute("publishers")
    public List<Publisher> publisherList() {
        return publisherService.findAll();
    }

    @PostMapping("/save")
    public String saveBook(Book book){
        bookService.save(book);
        return "redirect:/book";
    }
//    @RequestMapping("/add")
//    @ResponseBody
//    public String saveBook() {
//
//        Publisher publisher = publisherService.findOneById(1L);
//
//        Book book = new Book();
//        book.setTitle(this.faker.superhero().name());
//        Random random = new Random();
//        book.setRating(random.nextInt(10)+1);
//        book.setDescription(this.faker.lorem().fixedString(100)+"...");
//        book.setPublisher(publisher);
//
//        this.bookService.save(book);
//
//        return "Book saved at id: "+book.getId();
//    }

    @GetMapping()
    @Transactional
    public String allBooks(Model model){
        List<Book> bookList = this.bookService.findAll();
//        bookList.forEach(b -> Hibernate.initialize(b.getAuthors()));
        model.addAttribute("books", bookList);
        return "book/list";
    }
    @GetMapping("/rating/{rating}")
    public String allBooks(Model model, @PathVariable int rating){
        List<Book> bookList = this.bookService.getRatingList(rating);
        model.addAttribute("books", bookList);
        return "book/list";
    }
    @GetMapping("/publisherId/{publisherId}")
    public String allBooksByPublisherId(Model model, @PathVariable long publisherId){
        Publisher publisher = publisherService.findOneById(publisherId);
        List<Book> bookList = this.bookService.findAllWithThisPublisher(publisher);
        model.addAttribute("books", bookList);
        return "book/list";
    }
    @GetMapping("/publisherName/{publisherName}")
    public String allBooksByPublisherName(Model model, @PathVariable String publisherName){
        Publisher publisher = publisherService.findOneByName(publisherName);
        List<Book> bookList = this.bookService.findAllWithThisPublisher(publisher);
        model.addAttribute("books", bookList);
        return "book/list";
    }
    @GetMapping("/author/{authorId}")
    public String allBooksByAuthor(Model model, @PathVariable long authorId){
        Author author = authorService.findOneById(authorId);
        List<Book> bookList = this.bookService.findAllWithThisAuthor(author);
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