package org.example.demo.stream.api.controller;

import org.example.demo.stream.api.dao.PersonDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonController {
    @Autowired
    private PersonDAO personDAO;

    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    @GetMapping("/getAll")
    public String getAll(Model model) {
        model.addAttribute("people", personDAO.getAll());
        return "getAll";
    }

    @GetMapping("/sortPeopleByFullName")
    public String sortPeopleByFullName(Model model) {
        model.addAttribute("people", personDAO.sortPeopleByFullName());
        return "sortPeopleByFullName";
    }

    @GetMapping("/getSortedJobs")
    public String getSortedJobs(Model model) {
        model.addAttribute("jobs", personDAO.getSortedJobs());
        return "getSortedJobs";
    }

    @GetMapping("/getSortedCities")
    public String getSortedCities(Model model) {
        model.addAttribute("cities", personDAO.getSortedCities());
        return "getSortedCities";
    }
}
