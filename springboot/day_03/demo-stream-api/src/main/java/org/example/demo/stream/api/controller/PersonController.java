package org.example.demo.stream.api.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PersonController {
    @GetMapping("/")
    public String getHome() {
        return "index";
    }

    @GetMapping("/getAll")
    public String getAll() {
        return "getAll";
    }

    @GetMapping("/sortPeopleByFullName")
    public String sortPeopleByFullName() {
        return "sortPeopleByFullName";
    }

    @GetMapping("/getSortedJobs")
    public String getSortedJobs() {
        return "getSortedJobs";
    }

    @GetMapping("/getSortedCities")
    public String getSortedCities() {
        return "getSortedCities";
    }
}
