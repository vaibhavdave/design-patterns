package com.designpatterns.behavioral.chain;

/** First link: rejects requests with no auth token and never invokes the rest of the chain. */
public final class AuthenticationHandler extends RequestHandler {

    @Override
    public void handle(Request request) {
        request.recordVisit("AuthenticationHandler");
        if (!request.hasAuthToken()) {
            request.reject("Missing authentication token");
            return;
        }
        passToNext(request);
    }
}
