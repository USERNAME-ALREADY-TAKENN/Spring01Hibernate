package pl.coderslab.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.coderslab.entity.Person;
import pl.coderslab.model.Student;

@Controller
@RequestMapping("/student")
public class StudentController {

    @GetMapping("/add")
    public String addStudent(Model model) {
        model.addAttribute("student", new Student());
        return "student/form";
    }
    @PostMapping("/details")
    public String showDetails(@RequestParam Student student, Model model) {
        model.addAttribute("student", student);

        return "student/details";
    }
}
