package com.designpatterns.capstone.repository.plain;

import java.util.Objects;

/** Immutable value; {@link InMemoryBookDao#save} returns a new copy carrying the generated id. */
public final class Book {

    private final Long id;
    private final String title;
    private final String author;

    public Book(Long id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Book withId(Long newId) {
        return new Book(newId, title, author);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book book)) return false;
        return Objects.equals(id, book.id) && Objects.equals(title, book.title)
                && Objects.equals(author, book.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, author);
    }

    @Override
    public String toString() {
        return "Book{id=%s, title='%s', author='%s'}".formatted(id, title, author);
    }
}
