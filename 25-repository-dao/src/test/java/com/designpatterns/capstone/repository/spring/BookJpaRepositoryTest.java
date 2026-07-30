package com.designpatterns.capstone.repository.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * {@code @DataJpaTest} boots just the JPA slice of the context against an embedded H2 database and
 * wraps each test in a transaction that's rolled back afterward — no manual data cleanup needed.
 */
@DataJpaTest
class BookJpaRepositoryTest {

    @Autowired
    private BookJpaRepository bookJpaRepository;

    @Test
    void savePersistsAndAssignsAGeneratedId() {
        BookEntity saved = bookJpaRepository.save(new BookEntity("Effective Java", "Joshua Bloch"));

        assertThat(saved.getId()).isNotNull();
        assertThat(bookJpaRepository.findById(saved.getId())).isPresent();
    }

    @Test
    void findByAuthorIsDerivedFromTheMethodNameBySpringData() {
        bookJpaRepository.save(new BookEntity("Clean Code", "Robert C. Martin"));
        bookJpaRepository.save(new BookEntity("Clean Architecture", "Robert C. Martin"));
        bookJpaRepository.save(new BookEntity("Effective Java", "Joshua Bloch"));

        assertThat(bookJpaRepository.findByAuthor("Robert C. Martin")).hasSize(2);
    }
}
