package com.designpatterns.capstone.repository.plain;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

/**
 * A hand-written DAO with zero framework or database involved — a {@code Map} standing in for a
 * table. Swapping this for a JDBC- or JPA-backed implementation later would require no change to
 * any caller, because callers only ever depend on {@link BookDao}. Compare to {@code
 * spring/BookJpaRepository}, which gets the SAME abstraction generated for you.
 */
public class InMemoryBookDao implements BookDao {

    private final Map<Long, Book> storage = new ConcurrentHashMap<>();
    private final AtomicLong idSequence = new AtomicLong();

    @Override
    public Book save(Book book) {
        long id = book.getId() != null ? book.getId() : idSequence.incrementAndGet();
        Book saved = book.withId(id);
        storage.put(id, saved);
        return saved;
    }

    @Override
    public Optional<Book> findById(long id) {
        return Optional.ofNullable(storage.get(id));
    }

    @Override
    public List<Book> findAll() {
        return List.copyOf(storage.values());
    }

    @Override
    public void deleteById(long id) {
        storage.remove(id);
    }
}
