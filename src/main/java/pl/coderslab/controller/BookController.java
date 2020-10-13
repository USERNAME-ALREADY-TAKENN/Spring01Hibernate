package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import pl.coderslab.entity.Book;
import pl.coderslab.entity.Publisher;
import pl.coderslab.service.BookService;
import pl.coderslab.service.PublisherService;

import java.util.List;

@Controller
@RequestMapping("/book")
public class BookController {

    private BookService bookService;
    private PublisherService publisherService;

    @Autowired
    public BookController(BookService bookService, PublisherService publisherService) {
        this.bookService = bookService;
        this.publisherService = publisherService;
    }
    @RequestMapping("/add")
    @ResponseBody
    public String saveBook() {

        Publisher publisher = publisherService.findOneById(1L);

        Book book = new Book();
        book.setTitle("Ahaja");
        book.setRating(6);
        book.setDescription("Some description");
        book.setPublisher(publisher);

        this.bookService.save(book);

        return "Book saved at id: "+book.getId();
    }

    @GetMapping()
    @ResponseBody
    public String allBooks(Model model){
//        return this.bookService.findAll().toString();
        List<Book> bookList = this.bookService.findAllWithPublisher();
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