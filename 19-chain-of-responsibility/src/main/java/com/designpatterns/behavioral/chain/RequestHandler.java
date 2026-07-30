package com.designpatterns.behavioral.chain;

/**
 * A link in the chain. Concrete handlers call {@link #passToNext(Request)} to continue processing
 * or simply return without calling it to short-circuit — exactly the shape of a servlet
 * {@code Filter} calling (or not calling) {@code FilterChain.doFilter(...)}.
 */
public abstract class RequestHandler {

    protected RequestHandler next;

    public RequestHandler setNext(RequestHandler next) {
        this.next = next;
        return next;
    }

    public abstract void handle(Request request);

    protected void passToNext(Request request) {
        if (next != null) {
            next.handle(request);
        }
    }
}
