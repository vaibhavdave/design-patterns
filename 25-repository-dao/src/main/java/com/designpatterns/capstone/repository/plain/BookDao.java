package com.designpatterns.capstone.repository.plain;

import java.util.List;
import java.util.Optional;

/**
 * The abstraction application code depends on. Nothing here mentions SQL, a map, or any other
 * storage detail — that's the entire point of separating a DAO/Repository interface from its
 * implementation.
 */
public interface BookDao {

    Book save(Book book);

    Optional<Book> findById(long id);

    List<Book> findAll();

    void deleteById(long id);
}
