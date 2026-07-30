package com.designpatterns.capstone.repository.spring;

import com.designpatterns.capstone.repository.plain.Book;
import com.designpatterns.capstone.repository.plain.BookDao;
import com.designpatterns.capstone.repository.plain.InMemoryBookDao;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * Deliberately a separate {@code @Component} rather than living on {@link RepositoryDaoApplication}
 * itself: {@code @DataJpaTest} always registers the {@code @SpringBootConfiguration} class (to
 * bootstrap JPA config) but does NOT component-scan ordinary {@code @Component} beans — keeping
 * the demo seeding logic here means {@code BookJpaRepositoryTest} sees an empty database instead
 * of one already polluted by this runner.
 */
@Component
public class DemoRunner implements CommandLineRunner {

    private final BookJpaRepository bookJpaRepository;

    public DemoRunner(BookJpaRepository bookJpaRepository) {
        this.bookJpaRepository = bookJpaRepository;
    }

    @Override
    public void run(String... args) {
        System.out.println("-- Plain hand-written DAO (in-memory Map) --");
        BookDao bookDao = new InMemoryBookDao();
        bookDao.save(new Book(null, "Effective Java", "Joshua Bloch"));
        bookDao.save(new Book(null, "Clean Code", "Robert C. Martin"));
        bookDao.findAll().forEach(System.out::println);

        System.out.println();
        System.out.println("-- Spring Data JPA repository (H2, proxy generated at runtime) --");
        bookJpaRepository.save(new BookEntity("Effective Java", "Joshua Bloch"));
        bookJpaRepository.save(new BookEntity("Domain-Driven Design", "Eric Evans"));
        bookJpaRepository.save(new BookEntity("Clean Architecture", "Robert C. Martin"));

        bookJpaRepository.findAll()
                .forEach(b -> System.out.println(b.getId() + ": " + b.getTitle() + " by " + b.getAuthor()));

        System.out.println("By Robert C. Martin: "
                + bookJpaRepository.findByAuthor("Robert C. Martin").size());
    }
}
