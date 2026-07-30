package com.designpatterns.behavioral.chain;

/** Unconditionally observes the request and passes it on — never short-circuits. */
public final class LoggingHandler extends RequestHandler {

    @Override
    public void handle(Request request) {
        request.recordVisit("LoggingHandler");
        System.out.println("[log] " + request.getClientId() + " -> " + request.getPath());
        passToNext(request);
    }
}
