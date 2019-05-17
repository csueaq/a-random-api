package com.acala.service.pojo;

import java.util.Objects;

public class AuthorBookMap {

    private String authorId;
    private String bookId;

    public AuthorBookMap(String authorId, String bookId) {
        this.authorId = authorId;
        this.bookId = bookId;
    }

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AuthorBookMap that = (AuthorBookMap) o;
        return authorId.equals(that.authorId) &&
                bookId.equals(that.bookId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(authorId, bookId);
    }
}
