package vn.techmaster.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import vn.techmaster.demo.model.Book;

import java.util.ArrayList;
import java.util.List;

/*
 * package controller: Là nơi tiếp nhận request từ client gửi lên
 * @Controller: Có thể trả về giao diện (view template), các dữ liệu ở dạng JSON, XML,...
 * @RestController: Các dữ liệu ở dạng JSON, XML,...
 * */
@RestController
public class BookController {
    private final List<Book> bookList;

    public BookController() {
        bookList = new ArrayList<>();
        bookList.add(new Book("1", "Gone with the wind", "Cuong", 1945));
        bookList.add(new Book("2", "Chi Dau", "Nam Cao", 1943));
    }

    // GET : http://localhost:8080/books
    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookList;
    }

    // GET : http://localhost:8080/books/1
    // GET : http://localhost:8080/books/2
    // 1, 2 là ID của book
    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable String id) {
        System.out.println("ID = " + id);
        for (Book book : bookList) {
            if (book.getId().equals(id)) {
                return book;
            }
        }
        return null;
    }
}
