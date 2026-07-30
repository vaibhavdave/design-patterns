package com.designpatterns.capstone.mvc.spring;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void creatingThenFetchingABookRoundTripsThroughRealHttp() {
        CreateBookRequest request = new CreateBookRequest("Effective Java", "Joshua Bloch");

        ResponseEntity<Book> createResponse = restTemplate.postForEntity("/api/books", request, Book.class);
        assertThat(createResponse.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        Long id = createResponse.getBody().id();

        ResponseEntity<Book> getResponse = restTemplate.getForEntity("/api/books/" + id, Book.class);
        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(getResponse.getBody().title()).isEqualTo("Effective Java");
    }

    @Test
    void fetchingAnUnknownBookReturns404() {
        ResponseEntity<Book> response = restTemplate.getForEntity("/api/books/999999", Book.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}
