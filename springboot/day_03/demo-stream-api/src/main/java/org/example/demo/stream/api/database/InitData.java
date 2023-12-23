package org.example.demo.stream.api.database;

import org.example.demo.stream.api.utils.IFileReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

// CommandLineRunner là một interface chạy 1 lần sau khi Spring Boot khởi động
@Configuration
public class InitData implements CommandLineRunner {
    @Autowired
    private IFileReader fileReader;


    @Override
    public void run(String... args) throws Exception {
        PersonDB.people = fileReader.readFile("person.csv");
        System.out.println("people size : " + PersonDB.people.size());
    }
}
