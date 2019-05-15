package com.acala.service.controller;

import com.acala.service.dao.IDataAccessService;
import com.acala.service.pojo.Author;
import com.acala.service.pojo.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping(value = "/book/{id}")
    public Book getBook(@PathVariable String id,HttpServletResponse response) {
        response.setHeader("Cache-Control", "max-age=30");
        return dataAccessService.getBook(id);
    }

}
