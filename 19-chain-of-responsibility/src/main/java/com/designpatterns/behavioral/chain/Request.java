package com.designpatterns.behavioral.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * Carries state through the chain. {@code visitedHandlers} exists purely so tests and the demo
 * can observe how far a request actually traveled; a real HTTP request would use headers/attributes
 * for the equivalent of {@link #reject(String)}.
 */
public final class Request {

    private final String path;
    private final String clientId;
    private final boolean authTokenPresent;
    private final List<String> visitedHandlers = new ArrayList<>();
    private String rejectionReason;

    public Request(String path, String clientId, boolean authTokenPresent) {
        this.path = path;
        this.clientId = clientId;
        this.authTokenPresent = authTokenPresent;
    }

    public String getPath() {
        return path;
    }

    public String getClientId() {
        return clientId;
    }

    public boolean hasAuthToken() {
        return authTokenPresent;
    }

    public void recordVisit(String handlerName) {
        visitedHandlers.add(handlerName);
    }

    public List<String> getVisitedHandlers() {
        return List.copyOf(visitedHandlers);
    }

    public void reject(String reason) {
        this.rejectionReason = reason;
    }

    public boolean isRejected() {
        return rejectionReason != null;
    }

    public String getRejectionReason() {
        return rejectionReason;
    }
}
