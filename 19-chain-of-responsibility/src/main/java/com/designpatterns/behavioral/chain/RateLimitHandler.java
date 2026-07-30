package com.designpatterns.behavioral.chain;

import java.util.HashMap;
import java.util.Map;

/** Rejects a client once it exceeds {@code maxRequestsPerClient} requests through this instance. */
public final class RateLimitHandler extends RequestHandler {

    private final int maxRequestsPerClient;
    private final Map<String, Integer> requestCounts = new HashMap<>();

    public RateLimitHandler(int maxRequestsPerClient) {
        this.maxRequestsPerClient = maxRequestsPerClient;
    }

    @Override
    public void handle(Request request) {
        request.recordVisit("RateLimitHandler");
        int count = requestCounts.merge(request.getClientId(), 1, Integer::sum);
        if (count > maxRequestsPerClient) {
            request.reject("Rate limit exceeded for client " + request.getClientId());
            return;
        }
        passToNext(request);
    }
}
