package org.example.demo.thymeleaf.controller;

import org.example.demo.thymeleaf.model.Student;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class WebController {
    List<Student> studentList = List.of(
            new Student(1, "Nguyễn Văn A", "a@gmail.com", "0123456789", 10),
            new Student(2, "Nguyễn Văn B", "b@gmail.com", "0123456789", 9),
            new Student(3, "Nguyễn Văn C", "c@gmail.com", "0123456789", 8),
            new Student(4, "Nguyễn Văn D", "d@gmail.com", "0123456789", 7),
            new Student(5, "Nguyễn Văn E", "e@gamil.com", "0123456789", 6)
    );

    @GetMapping("/")
    public String getHomePage(Model model, @RequestParam(required = false) String rank) {
        Student student = new Student(1, "Nguyễn Văn A", "a@gmail.com", "0123456789", 10);
        model.addAttribute("student", student);

        List<Student> students = new ArrayList<>();
        if (rank != null) {
            if (rank.equals("gioi")) {
                students = studentList.stream()
                        .filter(s -> s.getScore() > 8)
                        .toList();
            } else if (rank.equals("kha")) {
                students = studentList.stream()
                        .filter(s -> s.getScore() <= 8)
                        .toList();
            }
        } else {
            students = studentList;
        }
        model.addAttribute("studentList", students);

        return "index";
    }

    // /student/1, /student/2
    @GetMapping("/student/{id}")
    public String getStudentDetailPage(@PathVariable int id, Model model) {
        Student student = studentList.stream()
                .filter(s -> s.getId() == id)
                .findFirst()
                .orElse(null);
        model.addAttribute("student", student);
        return "student-detail";
    }


    @GetMapping("/blog")
    public String getBlogPage() {
        return "admin/blog";
    }
}
