package com.designpatterns.creational.builder;

import java.time.Duration;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Immutable. A plain constructor for this class would have five parameters, two of them
 * optional-with-defaults and one a mutable {@code Map} — the classic "telescoping constructor"
 * pain that Builder exists to solve (Effective Java, Item 2). The constructor itself stays
 * private: the only supported way to obtain an instance is {@link Builder}, which validates
 * required fields and defensively copies the header map before this class ever sees it.
 */
public final class HttpRequest {

    private final String method;
    private final String url;
    private final Map<String, String> headers;
    private final String body;
    private final Duration timeout;

    private HttpRequest(Builder builder) {
        this.method = builder.method;
        this.url = builder.url;
        this.headers = Collections.unmodifiableMap(new LinkedHashMap<>(builder.headers));
        this.body = builder.body;
        this.timeout = builder.timeout;
    }

    public String method() {
        return method;
    }

    public String url() {
        return url;
    }

    public Map<String, String> headers() {
        return headers;
    }

    public String body() {
        return body;
    }

    public Duration timeout() {
        return timeout;
    }

    @Override
    public String toString() {
        return method + " " + url + " headers=" + headers + " timeout=" + timeout
                + (body != null ? " body=" + body : "");
    }

    /**
     * The idiomatic modern-Java fluent builder: a static nested class, chained setters that each
     * return {@code this}, and a terminal {@link #build()} that both validates and constructs the
     * immutable target. Nesting it inside {@code HttpRequest} (rather than a free-standing class)
     * is what lets the private {@code HttpRequest} constructor stay private — nested classes can
     * see their enclosing class's private members.
     */
    public static final class Builder {

        private String method;
        private String url;
        private final Map<String, String> headers = new LinkedHashMap<>();
        private String body;
        private Duration timeout = Duration.ofSeconds(30);

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder url(String url) {
            this.url = url;
            return this;
        }

        public Builder header(String name, String value) {
            this.headers.put(name, value);
            return this;
        }

        public Builder body(String body) {
            this.body = body;
            return this;
        }

        public Builder timeout(Duration timeout) {
            this.timeout = timeout;
            return this;
        }

        public HttpRequest build() {
            if (method == null) {
                throw new IllegalStateException("method is required");
            }
            if (url == null) {
                throw new IllegalStateException("url is required");
            }
            return new HttpRequest(this);
        }
    }
}
