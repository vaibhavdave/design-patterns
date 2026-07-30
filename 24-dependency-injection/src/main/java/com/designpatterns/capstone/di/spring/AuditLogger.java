package com.designpatterns.capstone.di.spring;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

import java.util.ArrayList;
import java.util.List;

/**
 * A plain bean with no constructor-injected dependencies, used to make the container's bean
 * lifecycle visible: {@link #init()} runs once the bean's properties/dependencies are fully set,
 * {@link #shutdown()} runs when the container closes. {@link #events} lets tests assert the
 * lifecycle actually ran, in order, without parsing log output.
 */
public class AuditLogger {

    private final List<String> events = new ArrayList<>();

    @PostConstruct
    public void init() {
        events.add("initialized");
    }

    public void log(String message) {
        events.add("logged: " + message);
    }

    @PreDestroy
    public void shutdown() {
        events.add("destroyed");
    }

    public List<String> getEvents() {
        return List.copyOf(events);
    }
}
