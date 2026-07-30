package com.designpatterns.capstone.mvc.spring;

/** The request body shape — deliberately a separate type from {@link Book} so callers can never supply an id. */
public record CreateBookRequest(String title, String author) {
}
