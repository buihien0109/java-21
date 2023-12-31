package vn.techmaster.demo.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import vn.techmaster.demo.utils.IFileReader;
import vn.techmaster.demo.utils.JsonFileReader;

// CommandLineRunner là một interface chạy 1 lần sau khi Spring Boot khởi động
@Configuration
public class InitData implements CommandLineRunner {
    @Autowired
    private IFileReader fileReader;


    @Override
    public void run(String... args) throws Exception {
        BookDB.bookList = fileReader.readFile("book.csv");
        System.out.println("Book size : " + BookDB.bookList.size());

        BookDB.bookList.forEach(System.out::println);
    }
}
