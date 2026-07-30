package com.designpatterns.creational.builder;

import org.junit.jupiter.api.Test;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class HttpRequestBuilderTest {

    @Test
    void buildsARequestWithAllOptionalFieldsPopulated() {
        HttpRequest request = new HttpRequest.Builder()
                .method("POST")
                .url("https://api.example.com/orders")
                .header("Content-Type", "application/json")
                .body("{}")
                .timeout(Duration.ofSeconds(5))
                .build();

        assertThat(request.method()).isEqualTo("POST");
        assertThat(request.url()).isEqualTo("https://api.example.com/orders");
        assertThat(request.headers()).containsEntry("Content-Type", "application/json");
        assertThat(request.body()).isEqualTo("{}");
        assertThat(request.timeout()).isEqualTo(Duration.ofSeconds(5));
    }

    @Test
    void appliesADefaultTimeoutWhenNoneIsSpecified() {
        HttpRequest request = new HttpRequest.Builder()
                .method("GET")
                .url("https://api.example.com/orders")
                .build();

        assertThat(request.timeout()).isEqualTo(Duration.ofSeconds(30));
    }

    @Test
    void rejectsBuildWhenMethodIsMissing() {
        HttpRequest.Builder builder = new HttpRequest.Builder().url("https://api.example.com");

        assertThatThrownBy(builder::build)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("method");
    }

    @Test
    void rejectsBuildWhenUrlIsMissing() {
        HttpRequest.Builder builder = new HttpRequest.Builder().method("GET");

        assertThatThrownBy(builder::build)
                .isInstanceOf(IllegalStateException.class)
                .hasMessageContaining("url");
    }

    @Test
    void headersMapReturnedByTheBuiltRequestIsUnmodifiable() {
        HttpRequest request = new HttpRequest.Builder()
                .method("GET")
                .url("https://api.example.com")
                .header("X-Trace", "abc")
                .build();

        assertThatThrownBy(() -> request.headers().put("X-Injected", "evil"))
                .isInstanceOf(UnsupportedOperationException.class);
    }
}
