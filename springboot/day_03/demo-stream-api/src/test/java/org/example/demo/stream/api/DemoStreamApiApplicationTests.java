package org.example.demo.stream.api;

import org.example.demo.stream.api.dao.PersonDAO;
import org.example.demo.stream.api.model.Person;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoStreamApiApplicationTests {
    @Autowired
    private PersonDAO personDAO;

    @Test
    void test_get_all() {
        List<Person> people = personDAO.getAll();
        personDAO.printListPeople(people);
    }

}
