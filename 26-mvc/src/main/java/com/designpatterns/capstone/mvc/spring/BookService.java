package com.designpatterns.capstone.mvc.spring;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * The "M" in MVC — business logic and state, deliberately with zero knowledge of HTTP. Nothing
 * here imports anything from {@code org.springframework.web}; it could be called from a CLI, a
 * scheduled job, or a different Controller entirely without change.
 */
@Service
public class BookService {

    private final Map<Long, Book> books = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong();

    public Book create(CreateBookRequest request) {
        long id = idSequence.incrementAndGet();
        Book book = new Book(id, request.title(), request.author());
        books.put(id, book);
        return book;
    }

    public List<Book> findAll() {
        return List.copyOf(books.values());
    }

    public Optional<Book> findById(long id) {
        return Optional.ofNullable(books.get(id));
    }
}
