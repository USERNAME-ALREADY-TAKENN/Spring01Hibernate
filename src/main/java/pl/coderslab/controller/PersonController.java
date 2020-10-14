package pl.coderslab.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Person;
import pl.coderslab.service.PersonService;

@Controller
@RequestMapping("/person")
public class PersonController {

    private PersonService personService;

    @Autowired
    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/add")
    public String formAdd(Model model){
        model.addAttribute("person", new Person());
        return "person/form";
    }

    @GetMapping("/edit/{id}")
    public String formAdd(@PathVariable long id, Model model){
        model.addAttribute("person", personService.findOneById(id));
        return "person/form";
    }

    @PostMapping("/save")
    public String save(@ModelAttribute Person person ){
        personService.save(person);
        return "person/details";
    }

}