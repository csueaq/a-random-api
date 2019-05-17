package com.acala.service.dao.impl;

import com.acala.service.controller.request.BookRequest;
import com.acala.service.dao.IDataAccessService;
import com.acala.service.pojo.Author;
import com.acala.service.pojo.AuthorBookMap;
import com.acala.service.pojo.Book;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DataAccessService implements IDataAccessService {


    List<Book> books = new ArrayList<>();
    List<Author> authors = new ArrayList<>();
    List<AuthorBookMap> authorBookMap = new ArrayList<>();

    @PostConstruct
    public void setup() throws Exception {

        Author author1 = new Author("a1", "J.K. Rowling");
        Author author2 = new Author("a2", "Michael Crichton");
        Book book1 = new Book("b1", "Harry Potter and the Chamber of Secrets", "a1");
        Book book2 = new Book("b2", "Jurassic Park", "a2");
        Book book3 = new Book("b3", "some random book", "a1");
        AuthorBookMap authorBookMap1 = new AuthorBookMap("a1", "b1");
        AuthorBookMap authorBookMap2 = new AuthorBookMap("a1", "b3");
        AuthorBookMap authorBookMap3 = new AuthorBookMap("a2", "b2");

        books.add(book1);
        books.add(book2);
        books.add(book3);
        authors.add(author1);
        authors.add(author2);
        authorBookMap.add(authorBookMap1);
        authorBookMap.add(authorBookMap2);
        authorBookMap.add(authorBookMap3);
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
        return books.stream().filter(b -> b.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public Author getAuthor(String id) {
        return authors.stream().filter(a -> a.getId().equals(id)).findAny().orElse(null);
    }

    @Override
    public Book create(BookRequest bookRequest) {
        Book book = null;
        if(getAuthor(bookRequest.getAuthor()) != null) {
            book = new Book(UUID.randomUUID().toString(), bookRequest.getTitle(), bookRequest.getAuthor());
            books.add(book);
            authorBookMap.add(new AuthorBookMap(bookRequest.getAuthor(), book.getId()));
        }

        return book;
    }

    @Override
    public Boolean delete(String id) {
        Boolean result = false;
        Book book = books.stream().filter(b -> b.getId().equals(id)).findAny().orElse(null);
        if(book != null) {
            result = books.remove(book);
            AuthorBookMap authorBookMapTemp = new AuthorBookMap(book.getAuthor(), book.getId());
            authorBookMap.remove(authorBookMapTemp);
        }
        return result;
    }

    @Override
    public Book update(BookRequest bookRequest) {

        Book book = books.stream().filter(b -> b.getId().equals(bookRequest.getId())).findAny().orElse(null);
        Book updatedBook = null;
        if(book != null) {
            books.remove(book);
            updatedBook = new Book(bookRequest.getId(), bookRequest.getTitle(), bookRequest.getAuthor());
            books.add(updatedBook);
            AuthorBookMap authorBookMapOld = new AuthorBookMap(book.getAuthor(),book.getId());
            authorBookMap.remove(authorBookMapOld);
            authorBookMap.add(new AuthorBookMap(updatedBook.getAuthor(),updatedBook.getId()));
        }
        return updatedBook;
    }

    @Override
    public List<String> getBookIdByAuthorId(String authorId) {
        return authorBookMap.stream().filter(x -> x.getAuthorId().equals(authorId)).map(x->x.getBookId()).collect(Collectors.toList());
    }

    @Override
    public List<String> getAuthorIdByBookId(String bookId) {
        return authorBookMap.stream().filter(x -> x.getBookId().equals(bookId)).map(x->x.getAuthorId()).collect(Collectors.toList());

    }
}
