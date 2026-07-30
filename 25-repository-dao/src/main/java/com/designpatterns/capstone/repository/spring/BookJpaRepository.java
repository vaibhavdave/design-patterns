package com.designpatterns.capstone.repository.spring;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * No implementation class exists anywhere in this codebase. At startup, Spring Data generates one
 * at runtime — a dynamic proxy implementing this interface, translating {@code save}, {@code
 * findById}, {@code findAll}, {@code deleteById} (inherited from {@link JpaRepository}) and the
 * derived query {@link #findByAuthor(String)} (parsed from its method name) into JPA/SQL calls.
 * This is the exact same mechanism as {@code 10-proxy}'s Spring AOP example — a generated stand-in
 * object implementing an interface — applied to data access instead of cross-cutting logging.
 */
public interface BookJpaRepository extends JpaRepository<BookEntity, Long> {

    List<BookEntity> findByAuthor(String author);
}
