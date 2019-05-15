package com.acala.service.dao.impl;

import com.acala.service.dao.IDataAccessService;
import com.acala.service.pojo.Author;
import com.acala.service.pojo.Book;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DataAccessService implements IDataAccessService {


    List<Book> books = new ArrayList<>();
    List<Author> authors = new ArrayList<>();

    @PostConstruct
    public void setup() throws Exception {

        Author author1 = new Author("a1", "J.K. Rowling", Arrays.asList("b1","b3"));
        Author author2 = new Author("a2", "Michael Crichton", Arrays.asList("b2"));
        Book book1 = new Book("b1", "Harry Potter and the Chamber of Secrets", "a1");
        Book book2 = new Book("b2", "Jurassic Park", "a2");
        Book book3 = new Book("b3", "some random book", "a1");


        books.add(book1);
        books.add(book2);
        books.add(book3);
        authors.add(author1);
        authors.add(author2);
    }
    @Override
    public List<Book> getBooks() {
        return books;
    }

    @Override
    public List<Author> getAuthors() {
        return authors;
    }

    @Override
    public Book getBook(String id) {
        return books.stream().filter(b -> b.getId().equals(id)).findAny().get();
    }

    @Override
    public Author getAuthor(String id) {
        return authors.stream().filter(a -> a.getId().equals(id)).findAny().get();
    }
}
