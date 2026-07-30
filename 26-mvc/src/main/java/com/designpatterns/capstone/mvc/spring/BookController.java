package com.designpatterns.capstone.mvc.spring;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;

/**
 * The "C" in MVC — translates HTTP requests into {@link BookService} calls and results back into
 * HTTP responses. It contains no business rules of its own (that all lives in {@link
 * BookService}). There's no separate "View" class here: {@code @RestController} serializes the
 * return value straight to JSON, which is the response body — in a REST API, the JSON
 * representation IS the view. A server-rendered app would swap this for a template engine (e.g.
 * Thymeleaf) resolving a template name to HTML instead; the Controller/Service split stays
 * identical either way.
 */
@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> findAll() {
        return bookService.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findById(@PathVariable long id) {
        return bookService.findById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> create(@RequestBody CreateBookRequest request) {
        Book created = bookService.create(request);
        return ResponseEntity.created(URI.create("/api/books/" + created.id())).body(created);
    }
}
