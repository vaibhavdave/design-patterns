package com.designpatterns.capstone.repository.plain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InMemoryBookDaoTest {

    private final BookDao bookDao = new InMemoryBookDao();

    @Test
    void saveAssignsAnIdAndFindByIdReturnsIt() {
        Book saved = bookDao.save(new Book(null, "Effective Java", "Joshua Bloch"));

        assertThat(saved.getId()).isNotNull();
        assertThat(bookDao.findById(saved.getId())).contains(saved);
    }

    @Test
    void deleteByIdRemovesTheBook() {
        Book saved = bookDao.save(new Book(null, "Clean Code", "Robert C. Martin"));

        bookDao.deleteById(saved.getId());

        assertThat(bookDao.findById(saved.getId())).isEmpty();
    }

    @Test
    void findAllReturnsEverySavedBook() {
        bookDao.save(new Book(null, "Book A", "Author A"));
        bookDao.save(new Book(null, "Book B", "Author B"));

        assertThat(bookDao.findAll()).hasSize(2);
    }
}
