package com.acala.service.dao;

import com.acala.service.pojo.Author;
import com.acala.service.pojo.Book;

import java.util.List;

public interface IDataAccessService {

    List<Book> getBooks();
    List<Author> getAuthors();
    Book getBook(String id);
    Author getAuthor(String id);
}
