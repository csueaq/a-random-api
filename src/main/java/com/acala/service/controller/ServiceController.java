package com.acala.service.controller;

import com.acala.service.controller.request.BookRequest;
import com.acala.service.dao.IDataAccessService;
import com.acala.service.pojo.Author;
import com.acala.service.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class ServiceController {

    @Autowired
    IDataAccessService dataAccessService;

    @GetMapping(value = "/")
    public String getIndex() {
        return "Hello";
    }

    @GetMapping(value = "/ping")
    public String getPing() {
        return "OK";
    }

    @GetMapping(value = "/books")
    public List<Book> getBooks(HttpServletResponse response) {
        response.setHeader("Cache-Control", "max-age=30");
        return dataAccessService.getBooks();
    }

    @GetMapping(value = "/authors")
    public List<Author> getAuthors(HttpServletResponse response) {
        response.setHeader("Cache-Control", "max-age=30");
        return dataAccessService.getAuthors();
    }

    @GetMapping(value = "/author/{id}")
    public Author getAuthor(@PathVariable String id, HttpServletResponse response) {
        response.setHeader("Cache-Control", "max-age=30");
        return dataAccessService.getAuthor(id);
    }

    @GetMapping(value = "/author/books/{authorId}")
    public List<String> getBookIdsByAuthorId(@PathVariable String authorId, HttpServletResponse response) {
        response.setHeader("Cache-Control", "max-age=30");
        return dataAccessService.getBookIdByAuthorId(authorId);
    }

    @GetMapping(value = "/book/authors/{bookId}")
    public List<String> getAuthorIdsByBookId(@PathVariable String bookId, HttpServletResponse response) {
        response.setHeader("Cache-Control", "max-age=30");
        return dataAccessService.getAuthorIdByBookId(bookId);
    }

    @GetMapping(value = "/book/{id}")
    public Book getBook(@PathVariable String id,HttpServletResponse response) {
        response.setHeader("Cache-Control", "max-age=30");
        return dataAccessService.getBook(id);
    }

    @PostMapping(value = "book")
    public Book addBook(@RequestBody BookRequest request) {

        return dataAccessService.create(request);
    }

    @PutMapping(value = "book")
    public Book updateBook(@RequestBody BookRequest request) {

        return dataAccessService.update(request);
    }

    @DeleteMapping(value = "book/{id}")
    public Boolean deleteBook(@PathVariable String id) {

        return dataAccessService.delete(id);
    }

}
