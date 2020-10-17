package pl.coderslab.controller;

import com.github.javafaker.Faker;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Author;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;
import pl.coderslab.service.AuthorService;
import pl.coderslab.service.BookService;
import pl.coderslab.service.BookServiceDb;
import pl.coderslab.service.PublisherService;


import javax.transaction.Transactional;
import javax.validation.Valid;
import java.util.List;
import java.util.Random;
import java.util.Set;

@Controller
@RequestMapping("/book")
public class BookController {

    private BookService bookService;
    private PublisherService publisherService;
    private AuthorService authorService;
    private Validator validator;
//    private Faker faker;

    @Autowired
    public BookController(BookService bookService, PublisherService publisherService, AuthorService authorService,
                          Validator validator) {
        this.bookService = bookService;
        this.publisherService = publisherService;
        this.authorService = authorService;
        this.validator = validator;
//        this.faker = new Faker();
    }

    @ModelAttribute("publishers")
    public List<Publisher> publisherList(){
        return publisherService.findAll();
    }

    @ModelAttribute("authors")
    public List<Author> authorList(){
        return authorService.findAll();
    }

    @GetMapping("/new")
    public String bookForm(Model model) {
        Book book = new Book();
        model.addAttribute("book", book);
        return "book/form";
    }

    @PostMapping("/save")
    public String saveBook(@Valid Book book, BindingResult validation){
        if(validation.hasErrors()){
            return "book/form";
        }

        bookService.save(book);
        return "redirect:/book";
    }
//    @Transactional
//    @GetMapping("/edit/{id}")
//    public String bookForm(@PathVariable long id ,Model model){
//        Book book = bookService.findOneByIdWithAllData(id);
//        model.addAttribute("book", book);
//        return "book/form";
//    }
    @GetMapping("/edit/{id}")
    public String bookForm(@PathVariable long id ,Model model){
        Book book = bookService.findOneByIdWithAllData(id);
        model.addAttribute("book", book);
        return "book/form";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(
            @PathVariable long id,
            Model model,
            @RequestParam(required = false, defaultValue = "false") boolean accept
    ){
        Book book = bookService.findOneById(id);
        if(!accept) {
            model.addAttribute("book", book);
            return "book/deletePrompt";
        }
        bookService.delete(book);
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


    @GetMapping("/test")
    public String testBooks(Model model){
        BookServiceDb bookServiceDb = (BookServiceDb) this.bookService;
        Set<Book> bookList = bookServiceDb.testRepo();
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


}