package com.designpatterns.behavioral.chain;

/** Builds a chain via {@code setNext(...)} and runs a passing and a failing request through it. */
public final class ChainDemo {

    public static void main(String[] args) {
        System.out.println("-- Request that passes the whole chain --");
        RequestHandler goodChain = buildChain();
        Request goodRequest = new Request("/orders", "client-1", true);
        goodChain.handle(goodRequest);
        System.out.println("Visited: " + goodRequest.getVisitedHandlers());
        System.out.println("Rejected: " + goodRequest.isRejected());

        System.out.println();
        System.out.println("-- Request rejected partway through (missing auth token) --");
        RequestHandler badChain = buildChain();
        Request badRequest = new Request("/orders", "client-2", false);
        badChain.handle(badRequest);
        System.out.println("Visited: " + badRequest.getVisitedHandlers());
        System.out.println("Rejected: " + badRequest.isRejected() + " (" + badRequest.getRejectionReason() + ")");
    }

    private static RequestHandler buildChain() {
        RequestHandler auth = new AuthenticationHandler();
        RequestHandler logging = new LoggingHandler();
        RequestHandler rateLimit = new RateLimitHandler(3);
        RequestHandler business = new BusinessLogicHandler();

        auth.setNext(logging).setNext(rateLimit).setNext(business);
        return auth;
    }
}
