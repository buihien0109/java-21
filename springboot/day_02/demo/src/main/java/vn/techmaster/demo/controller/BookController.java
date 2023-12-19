package vn.techmaster.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import vn.techmaster.demo.model.Book;
import vn.techmaster.demo.service.BookService;

import java.util.ArrayList;
import java.util.List;

/*
 * package controller: Là nơi tiếp nhận request từ client gửi lên
 * @Controller: Có thể trả về giao diện (view template), các dữ liệu ở dạng JSON, XML,...
 * @RestController: Các dữ liệu ở dạng JSON, XML,...
 * C1: @Controller + @ResponseBody = @RestController
 * C2: Sử dụng đối tượng ResponseEntity<T> để trả về dữ liệu ở dạng JSON, XML,... : Là class đại diện cho response
 * */
@RestController
//@Controller
public class BookController {
    @Autowired
    private BookService bookService;

    // GET : http://localhost:8080/books
//    @ResponseStatus(HttpStatus.CREATED) // Trả về status code 201
//    @ResponseBody
//    @GetMapping("/books")
//    public List<Book> getBooks() {
//        return bookList;
//    }

//    @GetMapping("/home")
//    public String getHome() {
//        return "home";
//    }

    @GetMapping("/books")
    public ResponseEntity<List<Book>> getBooks() {
        List<Book> bookList = bookService.getAllBooks();
        return new ResponseEntity<>(bookList, HttpStatus.CREATED);
    }

    // GET : http://localhost:8080/books/1
    // GET : http://localhost:8080/books/2
    // 1, 2 là ID của book
    @ResponseBody
    @GetMapping("/books/{id}")
    public Book getBookById(@PathVariable String id) {
        return bookService.getBookById(id);
    }
}
